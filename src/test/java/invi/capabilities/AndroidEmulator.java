package invi.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class AndroidEmulator implements Device {
    private DesiredCapabilities dc = new DesiredCapabilities();

    public AndroidEmulator() {
        File file = new File(Device.APK_PATH);
        String apkPath = file.getAbsoluteFile().getPath();

        dc.setCapability("platformName", "Android");
        dc.setCapability("platformVersion", "11");
        dc.setCapability("appActivity", ".splash.SplashActivity");
        dc.setCapability("app", apkPath);
        dc.setCapability("appPackage", "com.kiksoft.invi");
//        dc.setCapability("deviceName", "Pixel 3 API 30");
        dc.setCapability("allowTestPackages", "true");
        dc.setCapability("automationName", "UiAutomator2");
    }

    public DesiredCapabilities getCapabilities() {
        return dc;
    }
}
