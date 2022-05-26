package invi.runners;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.devicefarm.AWSDeviceFarmClient;
import com.amazonaws.services.devicefarm.model.*;
import invi.exceptions.IncorrectDeviceFarmUploadException;
import invi.exceptions.InvalidTestRunArgException;
import invi.utils.PropertiesHandler;
import invi.utils.System;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class AwsRunner implements TestRunner {
    private static final Logger LOGGER = Logger.getLogger(AwsRunner.class.getName());
    private PropertiesHandler propertiesHandler;

    private static final String UPLOAD_STATUS_SUCCEEDED = "SUCCEEDED";
    private static final String UPLOAD_STATUS_FAILED = "FAILED";
    private static final String RUN_STATUS_COMPLETED = "COMPLETED";
    private static final String RUN_STATUS_ERRORED = "ERRORED";
    private static final String APK_PATH = "app-debug.apk";
    private static final String IPA_PATH = "";
    private static final String TEST_PACKAGE = "target/zip-with-dependencies.zip";
    private static final String TEST_PACKAGE_TYPE = "APPIUM_JAVA_TESTNG_TEST_PACKAGE";
    private static final String TEST_TYPE = "APPIUM_JAVA_TESTNG";
    private static final String APP_TYPE_ANDROID = "ANDROID_APP";
    private static final String APP_TYPE_IOS = "IOS_APP";
    private String projectArn;
    private String testSpecAndroidArn;
    private String testSpecIosArn;
    private String singleDevicePoolArn;
    private AWSDeviceFarmClient deviceFarmClient;
    private OkHttpClient httpClient = new OkHttpClient();

    public AwsRunner(String deviceSystem) {
        propertiesHandler = new PropertiesHandler();
        projectArn = propertiesHandler.getProperty("aws.properties", "aws.project.arn");
        testSpecAndroidArn = propertiesHandler.getProperty("aws.properties", "aws.testspec.android.arn");
        testSpecIosArn = propertiesHandler.getProperty("aws.properties", "aws.testspec.ios.arn");
        singleDevicePoolArn = propertiesHandler.getProperty("aws.properties", "aws.pool.arn");

        if (deviceSystem.equals("Android")) {
            System.ANDROID.setActive(true);
        } else if (deviceSystem.equals("iOS")) {
            System.IOS.setActive(true);
        } else throw new InvalidTestRunArgException(deviceSystem + " is not valid device system. Choose between Android and iOS");
    }

    //        upload app file to device farm
    private String uploadDeviceFarmFile(String path, String type) {
        File file = new File(path);
        String uploadName = dateUtils.getCurrentDate() + "-" + file.getName();
        String contentType = "application/octet-stream";

        CreateUploadRequest uploadRequest = new CreateUploadRequest()
                .withProjectArn(projectArn)
                .withType(type)
                .withName(uploadName)
                .withContentType(contentType);
        
        Upload upload = deviceFarmClient.createUpload(uploadRequest).getUpload();

        String uploadArn = upload.getArn();
        String uploadUrl = upload.getUrl();
        RequestBody body = RequestBody.create(file, MediaType.parse(contentType));
        Request request = new Request.Builder()
                .url(uploadUrl)
                .put(body)
                .build();
        Response response = null;
        LOGGER.info("Upload " + file.getName() + " started at " + LocalDateTime.now());
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        if (!response.isSuccessful()) {
            LOGGER.severe("Could not upload " + file.getName() +
                    "to Device Farm. Request failed with message" + response.message());
            throw new IncorrectDeviceFarmUploadException(
                    "The upload status code is " + String.valueOf(response.code()) +
                            ". DeviceFarm says reason is: \n" + response.message()
            );
        }
        while (true) {
            if (upload.getStatus().equals(UPLOAD_STATUS_SUCCEEDED)) {
                break;
            } else if (upload.getStatus().equals(UPLOAD_STATUS_FAILED)) {
                String message = upload.getMessage();
                if(upload.getMessage() == null) {
                    message = upload.getMetadata();
                } else {
                    message = upload.getMessage();
                }
                throw new IncorrectDeviceFarmUploadException(
                        "The upload failed processing. DeviceFarm says reason is: \n"
                                + message
                );
            }
            upload = deviceFarmClient.getUpload(new GetUploadRequest().withArn(uploadArn)).getUpload();
        }
        return uploadArn;
    }

    public void run() {
        //   create client and upload files
        String accessKey = propertiesHandler.getProperty("aws.properties", "aws.access.key");
        String secretKey = propertiesHandler.getProperty("aws.properties", "aws.secret.key");
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        deviceFarmClient = (AWSDeviceFarmClient) AWSDeviceFarmClient
                .builder()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.US_WEST_2)
                .build();

        String appArn = null;
        String testSpecArn = null;
        if (System.ANDROID.isActive) {
            appArn = uploadDeviceFarmFile(APK_PATH, APP_TYPE_ANDROID);
            testSpecArn = testSpecAndroidArn;
        } else if (System.IOS.isActive) {
            appArn = uploadDeviceFarmFile(IPA_PATH, APP_TYPE_IOS);
            testSpecArn = testSpecIosArn;
        }
        LOGGER.info("App uploaded successfully. Upload arn is " + appArn);
        String testPackageArn = uploadDeviceFarmFile(TEST_PACKAGE, TEST_PACKAGE_TYPE);
        LOGGER.info("Test package uploaded successfully. Package arn is " + testPackageArn);

        // start test run
        ScheduleRunTest scheduleRunTest = new ScheduleRunTest()
                .withType(TEST_TYPE)
                .withTestSpecArn(testSpecArn)
                .withTestPackageArn(testPackageArn);

        ScheduleRunRequest runRequest = new ScheduleRunRequest()
                .withProjectArn(projectArn)
                .withAppArn(appArn)
                .withDevicePoolArn(singleDevicePoolArn)
                .withName("test run " + dateUtils.getCurrentDate())
                .withTest(scheduleRunTest);

        ScheduleRunResult runResult = deviceFarmClient.scheduleRun(runRequest);

        String runArn = runResult.getRun().getArn();
        String runStartTime = dateUtils.getCurrentDate();
        LOGGER.info("Scheduled test run " + runArn);

        try {
            while (true) {
                Run run = deviceFarmClient.getRun(new GetRunRequest().withArn(runArn)).getRun();
                if (run.getStatus().equals(RUN_STATUS_COMPLETED) || run.getStatus().equals(RUN_STATUS_ERRORED)) {
                    LOGGER.info("Test run finished with status " + run.getStatus());
                    break;
                }
            }
        } catch (Exception e) {
            deviceFarmClient.stopRun(new StopRunRequest().withArn(runArn));
            java.lang.System.exit(1);
        }

        // collect artifacts
    }
}
