package invi.utils;

import io.appium.java_client.AppiumDriver;

public class Waiter {
    public static void driverWait(AppiumDriver driver, int milliseconds) {
        try {
            synchronized (driver) {
                driver.wait(milliseconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
