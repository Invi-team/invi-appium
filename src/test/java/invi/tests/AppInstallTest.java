package invi.tests;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import invi.capabilities.AndroidEmulator;
import invi.listeners.TestListener;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({
        ExtentITestListenerClassAdapter.class,
        TestListener.class
})
public class AppInstallTest {
    private static final String APP_PATH = "/Users/mateusz/git/invi-android/app/build/outputs/apk/debug/app-debug.apk";

    @Test
    public void appInstallTest() {
        MobileDriver<MobileElement> driver = new AndroidEmulator().getDriver();

        Assert.assertTrue(driver.isAppInstalled(APP_PATH));
    }
}
