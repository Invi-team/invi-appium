package invi.tests.auth;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import invi.data.providers.auth.LogInData;
import invi.driver.DeviceManager;
import invi.listeners.TestListener;
import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SignInPage;
import invi.utils.Constants;
import invi.utils.PropertiesHandler;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Listeners({
        ExtentITestListenerClassAdapter.class,
        TestListener.class
})
public class LogInTest {
    private static final Logger LOGGER = Logger.getLogger(PropertiesHandler.class.getName());
    private MobileDriver<MobileElement> driver;
    private DeviceManager deviceManager = new DeviceManager();

    @BeforeMethod
    public void setUp() {
        Map<String, String> params = new HashMap<>();
        driver = deviceManager.getDriver();
        driver.resetApp();
    }

    @Test(dataProvider = "logInData", dataProviderClass = LogInData.class)
    public void logInTest(String email, String password, String incorrectEmail, String incorrectPassword) {
        LandingPage landingPage = new LandingPage(driver);
        SignInPage signInPage = new SignInPage(driver);
        MainPage mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        landingPage.selectSignIn();
        signInPage.signIn(Constants.EMPTY, incorrectPassword);
        Assert.assertEquals(Constants.EMPTY_FIELD_ERROR_MESSAGE, signInPage.getEmailInputErrorLabel().getText());

        signInPage.signIn(incorrectEmail, incorrectPassword);
        Assert.assertEquals(Constants.EMAIL_FORMAT_ERROR_MESSAGE, signInPage.getEmailInputErrorLabel().getText());

        signInPage.signIn(email, password);
        wait.until(visibilityOf(mainPage.getLogOutButton()));
        Assert.assertTrue(mainPage.getLogOutButton().isDisplayed());
    }

    @AfterTest
    public void teardown() {
    }
}
