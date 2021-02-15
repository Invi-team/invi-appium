package invi.utils;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestResult;

import java.lang.reflect.Field;

public class InviDriver {
    public static boolean DRIVER_ACCESSABLE = true;

    public static MobileDriver retrieveDriver(ITestResult iTestResult) {
        Class testClass = iTestResult.getTestClass().getRealClass();
        Field testField = null;
        MobileDriver<MobileElement> driver = null;

        try {
            testField = testClass.getDeclaredField("driver");
            testField.setAccessible(DRIVER_ACCESSABLE);
            driver = (MobileDriver<MobileElement>)testField.get(iTestResult.getInstance());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
