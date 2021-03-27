package invi.tests.auth;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import invi.driver.DeviceManager;
import invi.listeners.TestListener;
import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SignInPage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;


@Listeners({
        ExtentITestListenerClassAdapter.class,
        TestListener.class
})
public class LogInTest {
    private final static String CORRECT_EMAIL = "test.existing@email.com";
    private final static String INCORRECT_EMAIL = "incorrect.email";
    private final static String INCORRECT_PASSWORD = "zaqwsx";
    private final static String EMPTY = "";
    private final static String CORRECT_PASSWORD = "slimak";
    private final static String EMPTY_FIELD_ERROR_MESSAGE = "This field cannot be empty";
    private final static String EMAIL_FORMAT_ERROR_MESSAGE = "Wrong e-mail address format";

    private MobileDriver<MobileElement> driver;
    private DeviceManager deviceManager = new DeviceManager();

    @BeforeMethod
    public void setUp() {
        Map<String, String> params = new HashMap<>();
        params.put("packageName", "com.kiksoft.invi");
        params.put("activity", "splash.SplashActivity");

        driver = deviceManager.getDriver();
        driver.resetApp();
        deviceManager.initAppState(params);
    }

    @Test
    public void logInTest() {
        LandingPage landingPage = new LandingPage(this.driver);
        SignInPage signInPage = new SignInPage(this.driver);
        MainPage mainPage = new MainPage(this.driver);

        landingPage.selectSignIn();
        signInPage.signIn(EMPTY, INCORRECT_PASSWORD);
        Assert.assertEquals(EMPTY_FIELD_ERROR_MESSAGE, signInPage.getEmailInputErrorLabel().getText());

        signInPage.signIn(INCORRECT_EMAIL, INCORRECT_PASSWORD);
        Assert.assertEquals(EMAIL_FORMAT_ERROR_MESSAGE, signInPage.getEmailInputErrorLabel().getText());

        signInPage.signIn(CORRECT_EMAIL, CORRECT_PASSWORD);
        Assert.assertTrue(mainPage.getLogOutButton().isDisplayed());

    }
}
