package invi.listeners;

import invi.driver.DeviceManager;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class TestListener implements ITestListener {
    private static final Logger LOGGER = Logger.getLogger(TestListener.class.getName());
    private MobileDriver <MobileElement> driver;
    private DeviceManager deviceManager = new DeviceManager();

    private void takeScreenshot(ITestResult iTestResult) {
        java.io.File scrFile = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);

        StringBuilder pathBuilder = new StringBuilder()
                .append("./test-output/")
                .append(iTestResult.getName())
                .append("/")
                .append(this.composeNameByTestRun(iTestResult))
                .append(".png");

        try {
            FileUtils.copyFile(scrFile, new java.io.File(pathBuilder.toString()));
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private void writeMessage(ITestResult iTestResult) {
        String message = iTestResult.getThrowable().getMessage();

        StringBuilder pathBuilder = new StringBuilder()
                .append("./test-output/")
                .append(iTestResult.getName())
                .append("/")
                .append(this.composeNameByTestRun(iTestResult))
                .append(".txt");

        Path path = Paths.get(pathBuilder.toString());
        try {
            Files.write(path, message.getBytes());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
    }

    private String composeNameByTestRun(ITestResult iTestResult) {
        return new StringBuilder()
                .append(iTestResult.getTestClass().getRealClass().getSimpleName())
                .append(iTestResult.getStartMillis())
                .toString();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {}

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        this.takeScreenshot(iTestResult);
        this.writeMessage(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    @Override
    public void onStart(ITestContext iTestContext) {
        this.driver = deviceManager.getDriver();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {}

}

