package invi.listeners;

import invi.utils.InviDriver;
import invi.utils.InviFile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class TestListener implements ITestListener {
    private MobileDriver <MobileElement> driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {}

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        this.driver = InviDriver.retrieveDriver(iTestResult);
        Class testClass = iTestResult.getTestClass().getRealClass();
        Field testField = null;

        try {
            testField = testClass.getDeclaredField("driver");
            testField.setAccessible(true);
            this.driver = (MobileDriver<MobileElement>)testField.get(iTestResult.getInstance());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        File scrFile = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File("./test-output/screenshots/" + InviFile.composeNameByTestRun(iTestResult) + ".png"));
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
