package invi.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import invi.capabilities.AndroidEmulator;
import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SignUpPage;
import invi.reports.ExtentReport;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.junit.Test;
import java.util.Date;


public class SignUpTest {
    private final static String CORRECT_EMAIL = "test."+ String.valueOf(new Date().getTime()) +"@email.com";
    private final static String INCORRECT_EMAIL = "incorrect.email";
    private final static String INCORRECT_PASSWORD = "zaqwsx";
    private final static String EMPTY = "";
    private final static String CORRECT_PASSWORD = "slimak";
    private final static String EMPTY_FIELD_ERROR_MESSAGE = "This field cannot be empty";
    private final static String EMAIL_FORMAT_ERROR_MESSAGE = "Wrong e-mail address format";

    @Test
    public void signUpTest() {
        ExtentTest test = ExtentReport.getExtent().createTest("Sign up test", "Test incorrect and successful sigh up");
        MobileDriver<MobileElement> driver = new AndroidEmulator().getDriver();

        LandingPage landingPage = new LandingPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        MainPage mainPage = new MainPage(driver);

        test.log(Status.INFO, "Sign up test started");

        landingPage.selectSignUp();
        signUpPage.signUp(EMPTY, INCORRECT_PASSWORD);
        Assert.assertEquals(EMPTY_FIELD_ERROR_MESSAGE, signUpPage.getErrorInputLabel().getText());
        test.log(Status.PASS, "Sign up with empty email");

        signUpPage.signUp(INCORRECT_EMAIL, INCORRECT_PASSWORD);
        Assert.assertEquals(EMAIL_FORMAT_ERROR_MESSAGE, signUpPage.getErrorInputLabel().getText());
        test.log(Status.PASS, "Sign up with invalid format for email");

        signUpPage.signUp(CORRECT_EMAIL, CORRECT_PASSWORD);
        Assert.assertNotNull(mainPage.getLogOutButton());
        test.log(Status.PASS, "Sign up successfully");

        test.log(Status.INFO, "Sign up test completed");
        driver.quit();
    }
}
