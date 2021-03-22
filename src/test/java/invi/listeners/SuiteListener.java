package invi.listeners;

import invi.capabilities.Device;
import invi.driver.DeviceFactory;
import invi.driver.DeviceManager;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.logging.Logger;

public class SuiteListener implements ISuiteListener {
    private static final Logger LOGGER = Logger.getLogger(SuiteListener.class.getName());

    public void onStart(ISuite suite) {
        String deviceName = suite.getParameter("device");
        String deviceSystem = suite.getParameter("system");
        Device device = DeviceFactory.createDevice(deviceName);

        DeviceManager.setDeviceDriver(deviceSystem, device.getCapabilities());
    }

    public void onFinish(ISuite suite) {
        DeviceManager.quitDriver();
    }
}
