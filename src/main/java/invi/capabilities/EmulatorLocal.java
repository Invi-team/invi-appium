package invi.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class EmulatorLocal implements Device{
    private DesiredCapabilities dc = new DesiredCapabilities();

    public EmulatorLocal() {
        File file = new File(Device.APK_PATH);
        String apkPath = file.getAbsoluteFile().getPath();

        dc.setCapability("platformName", "Android");
        dc.setCapability("platfgit branchormVersion", "11");
        dc.setCapability("appActivity", ".splash.SplashActivity");
        dc.setCapability("app", apkPath);
        dc.setCapability("appPackage", "com.kiksoft.invi");
        dc.setCapability("deviceName", "Pixel 3 API 30");
        dc.setCapability("allowTestPackages", "true");
        dc.setCapability("automationName", "UiAutomator2");
        dc.setCapability("appWaitActivity", ".splash.SplashActivity, com.kiksoft.auth.welcome.WelcomeActivity");
        dc.setCapability("appWaitPackage", "com.kiksoft.invi, ");
        dc.setCapability("appWaitDuration", "50000");
    }

    public DesiredCapabilities getDc() {
        return dc;
    }
}
