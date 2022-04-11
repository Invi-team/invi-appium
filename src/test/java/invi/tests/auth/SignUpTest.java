package invi.tests.auth;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import invi.data.providers.auth.SignUpData;
import invi.driver.DeviceManager;
import invi.listeners.TestListener;
import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SettingsPage;
import invi.pages.open.SignUpPage;
import invi.utils.Constants;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.logging.Logger;

@Listeners({
        ExtentITestListenerClassAdapter.class,
        TestListener.class
})
public class SignUpTest {
    private static final Logger LOGGER = Logger.getLogger(SignUpTest.class.getName());
    private MobileDriver<MobileElement> driver;
    private DeviceManager deviceManager = new DeviceManager();

    @BeforeMethod
    public void setUp() {
        driver = deviceManager.getDriver();
        driver.resetApp();
    }

    @Test(dataProvider = "signUpData", dataProviderClass = SignUpData.class)
    public void signUpTest(String email, String password, String incorrectEmail, String incorrectPassword) {
        LandingPage landingPage = new LandingPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        MainPage mainPage = new MainPage(driver);
        SettingsPage settingsPage = new SettingsPage(driver);

        landingPage.selectSignUp();
        signUpPage.signUp(Constants.EMPTY, incorrectPassword);
        Assert.assertEquals(Constants.EMPTY_FIELD_ERROR_MESSAGE, signUpPage.getErrorInputLabel().getText());

        signUpPage.signUp(incorrectEmail, incorrectPassword);
        Assert.assertEquals(Constants.EMAIL_FORMAT_ERROR_MESSAGE, signUpPage.getErrorInputLabel().getText());

        signUpPage.signUp(email, password);
        mainPage.navigateToSettings();
        Assert.assertEquals(settingsPage.getEmail(), email);
    }
}
