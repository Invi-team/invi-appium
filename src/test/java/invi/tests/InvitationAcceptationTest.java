package invi.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import invi.capabilities.AndroidEmulator;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.junit.Test;


public class InvitationAcceptationTest {

    @Test
    public void invitationAcceptationTest() {
        ExtentTest test = ExtentReport.getExtent().createTest("Invitation acceptation test", "Test invitation acceptation by a wedding guest");

        MobileDriver<MobileElement> driver = new AndroidEmulator().getDriver();


        test.log(Status.INFO, "Invitation accpetation test started");
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.log(Status.PASS, "Log in successfully");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.log(Status.PASS, "Naviagation to event view");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.log(Status.PASS, "Invitation accepted");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.log(Status.PASS, "Reset acceptation");
        test.log(Status.INFO, "Invitation acceptation test completed");
    }
}
