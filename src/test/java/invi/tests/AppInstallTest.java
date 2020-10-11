package invi.tests;

import invi.capabilities.AndroidEmulator;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.junit.Test;

public class AppInstallTest {
    private static final String APP_PATH = "/Users/mateusz/git/invi-android/app/build/outputs/apk/debug/app-debug.apk";
    @Test
    public void appInstallTest() {
        MobileDriver<MobileElement> driver = new AndroidEmulator().getDriver();

        Assert.assertTrue(driver.isAppInstalled(APP_PATH));
    }
}
