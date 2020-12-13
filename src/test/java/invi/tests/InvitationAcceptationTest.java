package invi.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import invi.capabilities.AndroidEmulator;
import invi.listeners.TestListener;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

@Listeners({
        ExtentITestListenerClassAdapter.class,
        TestListener.class
})
public class InvitationAcceptationTest {

    @Test
    public void invitationAcceptationTest() {

        AndroidDriver<MobileElement> driver = null;
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("app", "/Users/mateusz/git/invi-android/app/build/outputs/apk/debug/app-debug.apk");
        dc.setCapability("platformName", "Android");
        dc.setCapability("deviceName", "Android Emulator");
        dc.setCapability("allowTestPackages", "true");

        try {
            driver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), dc);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.startActivity(new Activity("com.kiksoft.invi", "splash.SplashActivity").setOptionalIntentArguments("--es token 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI0OWZkMzU0Yi1jZGFiLTQxYmYtYTQ3NC0zMjllOGY1MWE2OTgiLCJleHAiOjE2NDEwOTY1MjN9.al2oh_e1cZVrlz_mpn1RsM3XMyLuK1M_wLO2PuPxRmQ'"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
