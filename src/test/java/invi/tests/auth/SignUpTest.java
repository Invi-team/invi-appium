package invi.tests.auth;

import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import invi.data.providers.auth.SignUpData;
import invi.driver.DeviceManager;
import invi.listeners.TestListener;
import invi.pages.guest.MainPage;
import invi.pages.open.LandingPage;
import invi.pages.open.SignUpPage;
import invi.utils.Constants;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

@Listeners({
        ExtentITestListenerClassAdapter.class,
        TestListener.class
})
public class SignUpTest {
    private MobileDriver<MobileElement> driver;
    private DeviceManager deviceManager = new DeviceManager();

    @BeforeMethod
    public void setUp() {
        Map<String, String> params = new HashMap<>();
        params.put("packageName", "com.kiksoft.invi");
        params.put("activity", ".splash.SplashActivity");

        driver = deviceManager.getDriver();
        driver.resetApp();
    }

    @Test(dataProvider = "signUpData", dataProviderClass = SignUpData.class)
    public void signUpTest(String email, String password, String incorrectEmail, String incorrectPassword) {
        LandingPage landingPage = new LandingPage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        MainPage mainPage = new MainPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, 20);

        landingPage.selectSignUp();
        signUpPage.signUp(Constants.EMPTY, incorrectPassword);
        Assert.assertEquals(Constants.EMPTY_FIELD_ERROR_MESSAGE, signUpPage.getErrorInputLabel().getText());

        signUpPage.signUp(incorrectEmail, incorrectPassword);
        Assert.assertEquals(Constants.EMAIL_FORMAT_ERROR_MESSAGE, signUpPage.getErrorInputLabel().getText());

        signUpPage.signUp(email, password);
        wait.until(visibilityOf(mainPage.getLogOutButton()));
        Assert.assertNotNull(mainPage.getLogOutButton());
    }
}
