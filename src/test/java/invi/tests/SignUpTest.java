package invi.tests;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import invi.driver.DeviceManager;
import invi.listeners.TestListener;
import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SignUpPage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Listeners({
        ExtentITestListenerClassAdapter.class,
        TestListener.class
})
public class SignUpTest {
    private final static String CORRECT_EMAIL = "test." + String.valueOf(new Date().getTime()) + "@email.com";
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
        Map <String, String> params = new HashMap<>();
        params.put("packageName", "com.kiksoft.invi");
        params.put("activity", "splash.SplashActivity");

        driver = deviceManager.getDriver();
        driver.resetApp();
        deviceManager.initAppState(params);
    }

    @Test
    public void signUpTest() {
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
    }
}
