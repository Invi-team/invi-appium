package invi.driver;

import invi.utils.PropertiesHandler;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Map;
import java.util.logging.Logger;

public class DeviceManager {
    private static final Logger LOGGER = Logger.getLogger(DeviceManager.class.getName());
    private static final String SYSTEM_ANDROID = "Android";
    private static final String SYSTEM_IOS = "iOS";
    private static ThreadLocal<MobileDriver> mobileDriver = new ThreadLocal<MobileDriver>();
    private static String mobileSystem;


    public DeviceManager() {
    }

    public MobileDriver getDriver() {
        return mobileDriver.get();
    }

    public void quitDriver(){
        mobileDriver.get().quit();
    }

    public void setDeviceDriver(String system, DesiredCapabilities capabilities) {
        mobileSystem = system;
        if(mobileSystem.equals(SYSTEM_ANDROID)) {
            try {
                String appiumHost = PropertiesHandler.getProperty("config.properties", "appium.host");
                mobileDriver.set(new AndroidDriver<MobileElement>(new URL(appiumHost), capabilities));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initAppState(Map<String, String> params) {
        if(mobileSystem.equals(SYSTEM_ANDROID)) {
            AndroidDriver driver = (AndroidDriver) mobileDriver.get();
            driver.startActivity(new Activity(params.get("packageName"), params.get("activity")));
        }
    }
}
