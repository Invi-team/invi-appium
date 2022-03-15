package invi.runners;


import com.amazonaws.ClientConfiguration;
import com.amazonaws.regions.Regions;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.devicefarm.AWSDeviceFarmClient;
import com.amazonaws.services.devicefarm.model.*;
import invi.exceptions.IncorrectDeviceFarmUploadException;
import invi.utils.System;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Logger;

public class AwsRunner implements TestRunner {
    private static final Logger LOGGER = Logger.getLogger(AwsRunner.class.getName());

    private static final String UPLOAD_STATUS_SUCCEEDED = "SUCCEEDED";
    private static final String UPLOAD_STATUS_FAILED = "FAILED";
    private static final String RUN_STATUS_COMPLETED = "COMPLETED";
    private static final String RUN_STATUS_ERRORED = "ERRORED";
    private static final String APK_PATH = "app-debug.apk";
    private static final String IPA_PATH = "";
    private static final String PROJECT_ARN = "arn:aws:devicefarm:us-west-2:371251128933:project:98c1412e-f7a9-43f6-8f81-76ed8c70e1ab";
    private static final String TEST_SPEC_ARN_ANDROID = "arn:aws:devicefarm:us-west-2:371251128933:upload:98c1412e-f7a9-43f6-8f81-76ed8c70e1ab/e23c0a3f-e1b6-4b26-befc-fc028bdb0dbe";
    private static final String TEST_SPEC_ARN_IOS = "";
    private static final String SINGLE_DEVICE_POOL_ARN = "arn:aws:devicefarm:us-west-2:371251128933:devicepool:98c1412e-f7a9-43f6-8f81-76ed8c70e1ab/edfd24ee-2594-47eb-8c1b-c5361f2abca8";
    private static final String TEST_PACKAGE = "zip-with-dependencies.zip";
    private static final String TEST_PACKAGE_TYPE = "APPIUM_JAVA_TESTNG_TEST_PACKAGE";
    private static final String TEST_TYPE = "APPIUM_JAVA_TESTNG";
    private static final String APP_TYPE_ANDROID = "ANDROID_APP";
    private static final String APP_TYPE_IOS = "IOS_APP";
    private AWSDeviceFarmClient deviceFarmClient;
    private OkHttpClient httpClient = new OkHttpClient();
    private Date date = new Date();

    public AwsRunner(String deviceSystem) {
        if (deviceSystem.equals("Android")) {
            System.ANDROID.setActive(true);
            LOGGER.info("Detected suite system: Android");
        } else if (deviceSystem.equals("iOS")) {
            System.IOS.setActive(true);
            LOGGER.info("Detected suite system: iOS");
        }
    }

    //        upload app file to device farm
    private String uploadDeviceFarmFile(String path, String type) {
        File file = new File(path);
        String uploadName = "upload-" + file.getName() + "-" + dateUtils.getCurrentDate();
        String contentType = "application/octet-stream";

        CreateUploadRequest uploadRequest = new CreateUploadRequest()
                .withProjectArn(PROJECT_ARN)
                .withType(type)
                .withName(uploadName)
                .withContentType(contentType);
        Upload uploadResult = deviceFarmClient.createUpload(uploadRequest).getUpload();

        String uploadArn = uploadResult.getArn();
        String uploadUrl = uploadResult.getUrl();
        RequestBody body = RequestBody.create(file, MediaType.parse(contentType));
        Request request = new Request.Builder()
                .url(uploadUrl)
                .put(body)
                .build();
        Response response = null;
        LOGGER.info("Upload " + file.getName() + "started at " + LocalDateTime.now());
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
            if (uploadResult.getStatus() == UPLOAD_STATUS_SUCCEEDED) {
                break;
            } else if (uploadResult.getStatus() == UPLOAD_STATUS_FAILED) {
                throw new IncorrectDeviceFarmUploadException(
                        "The upload failed processing. DeviceFarm says reason is: \n"
                                + uploadResult.getMessage()
                );
            }
            try {
                Thread.currentThread().wait(5000);
            } catch (InterruptedException e) {
                LOGGER.severe(e.getMessage());
            }
            uploadResult = deviceFarmClient.getUpload(new GetUploadRequest().withArn(uploadArn)).getUpload();
        }
        return uploadArn;
    }

    public void run() {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setRetryPolicy(new RetryPolicy(null, null, 25, true));

        //   create client and upload files
        deviceFarmClient = (AWSDeviceFarmClient) AWSDeviceFarmClient
                .builder()
                .withRegion(Regions.US_EAST_2)
                .withClientConfiguration(clientConfiguration)
                .build();

        String appArn = null;
        String testSpecArn = null;
        if (System.ANDROID.isActive) {
            appArn = uploadDeviceFarmFile(APK_PATH, APP_TYPE_ANDROID);
            testSpecArn = TEST_SPEC_ARN_ANDROID;
        } else if (System.IOS.isActive) {
            appArn = uploadDeviceFarmFile(IPA_PATH, APP_TYPE_IOS);
            testSpecArn = TEST_SPEC_ARN_IOS;
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
                .withProjectArn(PROJECT_ARN)
                .withAppArn(appArn)
                .withDevicePoolArn(SINGLE_DEVICE_POOL_ARN)
                .withName("test run " + dateUtils.getCurrentDate())
                .withTest(scheduleRunTest);

        ScheduleRunResult runResult = deviceFarmClient.scheduleRun(runRequest);

        String runArn = runResult.getRun().getArn();
        String runStartTime = dateUtils.getCurrentDate();
        LOGGER.info("Scheduled test run " + runArn);

        try {
            while (true) {
                Run run = deviceFarmClient.getRun(new GetRunRequest().withArn(runArn)).getRun();
                if (run.getStatus() == RUN_STATUS_COMPLETED || run.getStatus() == RUN_STATUS_ERRORED) {
                    LOGGER.info("Test run finished with status " + run.getStatus());
                }
            }
        } catch (Exception e) {
            deviceFarmClient.stopRun(new StopRunRequest().withArn(runArn));
            java.lang.System.exit(1);
        }

        // collect artifacts
    }
}
