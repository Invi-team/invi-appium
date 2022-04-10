package invi.listeners;

import invi.driver.DeviceManager;
import invi.utils.File;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class TestListener implements ITestListener {
    private MobileDriver <MobileElement> driver;
    private DeviceManager deviceManager = new DeviceManager();

    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {}

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        this.driver = deviceManager.getDriver();
        java.io.File scrFile = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new java.io.File("./test-output/screenshots/" + File.composeNameByTestRun(iTestResult) + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    @Override
    public void onStart(ITestContext iTestContext) {}

    @Override
    public void onFinish(ITestContext iTestContext) {}

}

