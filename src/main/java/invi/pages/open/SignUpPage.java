package invi.pages.open;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import invi.utils.Waiter;

import java.util.concurrent.TimeUnit;


public class SignUpPage {
    private AndroidDriver<AndroidElement> driver;
    private WebDriverWait wait;


    public SignUpPage() {
    }
    public SignUpPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,20);

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(className = "UIAKeyboard")
    private AndroidElement keyboard;

    @AndroidFindBy(id = "etEmail")
    private AndroidElement emailInput;

    @AndroidFindBy(id = "etPassword")
    private AndroidElement passwordInput;

    @AndroidFindBy(id = "btnSignUp")
    private AndroidElement signUpButton;

    @AndroidFindBy(id = "textinput_error")
    private AndroidElement errorInputLabel;

    public void hideKeyboardIfVisible() {
        if (keyboard != null) {
            driver.hideKeyboard();
        }
    }

    public void typeEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(this.emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
    }

    public void typePassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(this.passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickSignUp() {
        wait.until(ExpectedConditions.visibilityOf(this.signUpButton));
        signUpButton.click();
    }

    public void signUp(String email, String password) {
        hideKeyboardIfVisible();
        typeEmail(email);
        typePassword(password);
        clickSignUp();
    }

    public AndroidElement getErrorInputLabel() {
        return errorInputLabel;
    }
}
