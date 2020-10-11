package invi.tests;

import invi.capabilities.AndroidEmulator;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Assert;
import org.junit.Test;

public class AppInstallTest {
    private static final String APP_PATH = "/Users/mateusz/git/invi-android/app/build/outputs/apk/debug/app-debug.apk";
    @Test
    public void appInstallTest() {
        AndroidDriver<AndroidElement> driver = new AndroidEmulator().getDriver();

        Assert.assertTrue(driver.isAppInstalled(APP_PATH));
    }
}
