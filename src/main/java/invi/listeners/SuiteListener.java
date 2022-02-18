package invi.listeners;

import invi.capabilities.Device;
import invi.driver.DeviceFactory;
import invi.driver.DeviceManager;
import invi.utils.System;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.logging.Logger;

public class SuiteListener implements ISuiteListener {
    private static final Logger LOGGER = Logger.getLogger(SuiteListener.class.getName());
    private DeviceFactory deviceFactory = new DeviceFactory();
    private DeviceManager deviceManager = new DeviceManager();

    public void onStart(ISuite suite) {
        String deviceName = suite.getParameter("device");
        String deviceSystem = suite.getParameter("system");
        Device device = deviceFactory.createDevice(deviceName);

        if (deviceSystem.equals("Android")) {
            System.ANDROID.setActive(true);
            LOGGER.info("Detected suite system: Android");
        } else if (deviceSystem.equals("iOS")) {
            System.IOS.setActive(true);
            LOGGER.info("Detected suite system: iOS");
        }
        LOGGER.info("setting device driver for " + deviceSystem);
        deviceManager.setDeviceDriver(device.getDc());
    }

    public void onFinish(ISuite suite) {
        deviceManager.quitDriver();
    }
}
