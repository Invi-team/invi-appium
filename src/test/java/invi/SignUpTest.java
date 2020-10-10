package invi;

import invi.capabilities.AndroidEmulator;
import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SignInPage;
import invi.pages.open.SignUpPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
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
        AndroidDriver<AndroidElement> driver = new AndroidEmulator().getDriver();

        LandingPage landingPage = new LandingPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        MainPage mainPage = new MainPage(driver);

        landingPage.selectSignUp();
        signUpPage.signUp(EMPTY, INCORRECT_PASSWORD);
        Assert.assertEquals(EMPTY_FIELD_ERROR_MESSAGE, signUpPage.getErrorInputLabel().getText());
        signUpPage.signUp(INCORRECT_EMAIL, INCORRECT_PASSWORD);
        Assert.assertEquals(EMAIL_FORMAT_ERROR_MESSAGE, signUpPage.getErrorInputLabel().getText());
        signUpPage.signUp(CORRECT_EMAIL, CORRECT_PASSWORD);
        Assert.assertNotNull(mainPage.getLogOutButton());

        driver.quit();
    }
}
