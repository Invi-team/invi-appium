package invi.driver;

import invi.capabilities.Device;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class DeviceFactory {
    private static final Logger LOGGER = Logger.getLogger(DeviceFactory.class.getName());
    private static Device device;

    public static Device createDevice(String deviceName) {
        try {
            Class<?> driverClass = Class.forName(deviceName);
            Constructor<?> constructor = driverClass.getConstructor();
            device =  (Device) constructor.newInstance();
            LOGGER.info("Device created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Could not create device with device parameter: " + device);
        }
        return device;
    }
}
