package invi.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import invi.capabilities.AndroidEmulator;
import invi.reports.ExtentReport;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.junit.Test;

public class AppInstallTest {
    private static final String APP_PATH = "/Users/mateusz/git/invi-android/app/build/outputs/apk/debug/app-debug.apk";
    private static final String APP_PACKAGE = "com.kiksoft.invi";
    @Test
    public void appInstallTest() {
        ExtentTest test = ExtentReport.getExtent().createTest("App install test", "Ininstall and reinstall Invi app");
        test.log(Status.INFO, "App install test started");
        MobileDriver<MobileElement> driver = new AndroidEmulator().getDriver();

        driver.removeApp(APP_PACKAGE);
        Assert.assertFalse(driver.isAppInstalled(APP_PACKAGE));
        test.log(Status.PASS, "App uninstall");
        test.log(Status.FAIL, "App reinstall");

        driver.installApp(APP_PATH);
        Assert.assertTrue(driver.isAppInstalled(APP_PATH));
        test.log(Status.PASS, "App reinstall");
    }
}
