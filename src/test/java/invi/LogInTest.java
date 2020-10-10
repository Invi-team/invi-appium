package invi;

import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SignInPage;
import invi.capabilities.AndroidEmulator;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.Test;
import org.junit.Assert;


public class LogInTest {
    private final static String CORRECT_EMAIL = "test.existing@email.com";
    private final static String INORRECT_EMAIL = "incorrect.email";
    private final static String INCORRECT_PASSWORD = "zaqwsx";
    private final static String EMPTY = "";
    private final static String CORRECT_PASSWORD = "slimak";
    private final static String EMPTY_FIELD_ERROR_MESSAGE = "This field cannot be empty";
    private final static String EMAIL_FORMAT_ERROR_MESSAGE = "Wrong e-mail address format";

    @Test
    public void logInTest() {
        AndroidDriver<AndroidElement> driver = new AndroidEmulator().getDriver();

        LandingPage landingPage = new LandingPage(driver);
        SignInPage signInPage = new SignInPage(driver);
        MainPage mainPage = new MainPage(driver);


        landingPage.selectSignIn();
        signInPage.signIn(EMPTY, INCORRECT_PASSWORD);
        Assert.assertEquals(EMPTY_FIELD_ERROR_MESSAGE, signInPage.getEmailInputErrorLabel().getText());
        signInPage.signIn(INORRECT_EMAIL, INCORRECT_PASSWORD);
        Assert.assertEquals(EMAIL_FORMAT_ERROR_MESSAGE, signInPage.getEmailInputErrorLabel().getText());
        signInPage.signIn(CORRECT_EMAIL, CORRECT_PASSWORD);
        Assert.assertNotNull(mainPage.getLogOutButton());

        driver.quit();
    }
}
