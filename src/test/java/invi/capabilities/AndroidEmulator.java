package invi.capabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidEmulator {
    private AndroidDriver<MobileElement> driver = null;
    private DesiredCapabilities dc = new DesiredCapabilities();

    public AndroidEmulator() {
        dc.setCapability("platformName", "Android");
        dc.setCapability("appActivity", "splash.SplashActivity");
        dc.setCapability("app", "/Users/mateusz/git/invi-android/app/build/outputs/apk/debug/app-debug.apk");
        dc.setCapability("appPackage", "com.kiksoft.invi");
        dc.setCapability("deviceName", "Android Emulator");
        dc.setCapability("allowTestPackages", "true");
        dc.setCapability("automationName", "UiAutomator2");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), dc);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.startActivity(new Activity("com.kiksoft.invi", "splash.SplashActivity"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AndroidDriver<MobileElement> getDriver() {
        return driver;
    }
}
