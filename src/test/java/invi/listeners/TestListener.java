package invi.listeners;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.lang.reflect.Field;

public class TestListener implements ITestListener {
    private final boolean DRIVER_ACCESSABLE = true;
    private AppiumDriver driver = null;

    public TestListener() {

    }


    @Override
    public void onTestStart(ITestResult iTestResult) {}

    @Override
    public void onTestSuccess(ITestResult iTestResult) {}

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        this.driver = retrieveDriver(iTestResult);
        File scrFile = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {}

    @Override
    public void onStart(ITestContext iTestContext) {}

    @Override
    public void onFinish(ITestContext iTestContext) {}

    private AppiumDriver retrieveDriver(ITestResult iTestResult) {
        Class testClas = iTestResult.getTestClass().getRealClass();
        Field testField = null;
        AppiumDriver <?> driver = null;

        try {
            testField = testClas.getDeclaredField("driver");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        testField.setAccessible(this.DRIVER_ACCESSABLE);

        try {
        driver = (AppiumDriver<?>)testField.get(iTestResult.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return driver;
    }
}

