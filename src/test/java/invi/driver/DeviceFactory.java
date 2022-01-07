package invi.driver;

import invi.capabilities.Device;

import java.lang.reflect.Constructor;
import java.util.logging.Logger;

public class DeviceFactory {
    private static final Logger LOGGER = Logger.getLogger(DeviceFactory.class.getName());
    private Device device;

    public Device createDevice(String deviceName) {
        try {
            Class<?> driverClass = Class.forName(deviceName);
            Constructor<?> constructor = driverClass.getConstructor();
            device =  (Device) constructor.newInstance();
        } catch (Exception e) {
            LOGGER.info("Could not create device with device parameter: " + device);
            LOGGER.info(e.getMessage());
        }
        return device;
    }
}
