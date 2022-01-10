package invi.driver;

import invi.utils.PropertiesHandler;
import invi.utils.System;
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
    private static ThreadLocal<MobileDriver> mobileDriver = new ThreadLocal<MobileDriver>();
    private final PropertiesHandler propertiesHandler = new PropertiesHandler();

    public DeviceManager() {
    }

    public MobileDriver getDriver() {
        return mobileDriver.get();
    }

    public void quitDriver(){
        mobileDriver.get().quit();
    }

    public void setDeviceDriver(DesiredCapabilities capabilities) {

        if(System.ANDROID.isActive) {
            try {
                String appiumHost = propertiesHandler.getProperty("config.properties", "appium.host");
                mobileDriver.set(new AndroidDriver<MobileElement>(new URL(appiumHost), capabilities));
                LOGGER.info("Driver created: " + mobileDriver.toString());
            } catch (Exception e) {
                LOGGER.info("Could not create Android driver: " + e.getMessage());
            }
        }
    }

    public void initAppState(Map<String, String> params) {
        if(System.ANDROID.isActive) {
            AndroidDriver driver = (AndroidDriver) mobileDriver.get();
            if(params.containsKey("token")) {
                driver.startActivity(
                        new Activity(params.get("packageName"),
                        params.get("activity")).setOptionalIntentArguments(params.get("token"))
                );
            } else {
                driver.startActivity(new Activity(params.get("packageName"), params.get("activity")));
            }
        }
    }
}
