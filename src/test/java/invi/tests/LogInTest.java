package invi.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SignInPage;
import invi.capabilities.AndroidEmulator;
import invi.reports.ExtentReport;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.junit.Assert;



public class LogInTest {
    private final static String CORRECT_EMAIL = "test.existing@email.com";
    private final static String INCORRECT_EMAIL = "incorrect.email";
    private final static String INCORRECT_PASSWORD = "zaqwsx";
    private final static String EMPTY = "";
    private final static String CORRECT_PASSWORD = "slimak";
    private final static String EMPTY_FIELD_ERROR_MESSAGE = "This field cannot be empty";
    private final static String EMAIL_FORMAT_ERROR_MESSAGE = "Wrong e-mail address format";

    @Test
    public void logInTest() {
        ExtentTest test = ExtentReport.getExtent().createTest("Login test", "Test incorrect and successful log in");
        MobileDriver<MobileElement> driver = new AndroidEmulator().getDriver();

        LandingPage landingPage = new LandingPage(driver);
        SignInPage signInPage = new SignInPage(driver);
        MainPage mainPage = new MainPage(driver);

        test.log(Status.INFO, "Log in test started");

        landingPage.selectSignIn();
        signInPage.signIn(EMPTY, INCORRECT_PASSWORD);
        Assert.assertEquals(EMPTY_FIELD_ERROR_MESSAGE, signInPage.getEmailInputErrorLabel().getText());
        test.log(Status.PASS, "Log in with empty email");

        signInPage.signIn(INCORRECT_EMAIL, INCORRECT_PASSWORD);
        Assert.assertEquals(EMAIL_FORMAT_ERROR_MESSAGE, signInPage.getEmailInputErrorLabel().getText());
        test.log(Status.PASS, "Log in with invalid format for email");

        signInPage.signIn(CORRECT_EMAIL, CORRECT_PASSWORD);
        Assert.assertNotNull(mainPage.getLogOutButton());
        test.log(Status.PASS, "Log in successfully");

        test.log(Status.INFO, "Log in test completed");
        driver.quit();

    }
}
