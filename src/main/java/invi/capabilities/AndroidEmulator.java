package invi.capabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidEmulator {
    private AndroidDriver<AndroidElement> driver = null;
    private DesiredCapabilities dc = new DesiredCapabilities();

    public AndroidEmulator() {
        dc.setCapability("app", "/Users/mateusz/git/invi-android/app/build/outputs/apk/debug/app-debug.apk");
        dc.setCapability("platformName", "Android");
        dc.setCapability("deviceName", "Android Emulator");
        dc.setCapability("appPackage", "com.kiksoft.invi");
        dc.setCapability("appWaitActivity", "com.kiksoft.auth.welcome.WelcomeActivity");
        dc.setCapability("appWaitPackage", "com.kiksoft.invi");
        dc.setCapability("allowTestPackages", "true");
        dc.setCapability("newCommandTimeout", "120");

        try {
            driver = new AndroidDriver<AndroidElement>(new URL("http://localhost:4723/wd/hub"), dc);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AndroidDriver<AndroidElement> getDriver() {
        return driver;
    }
}