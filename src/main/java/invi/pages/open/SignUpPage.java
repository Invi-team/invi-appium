package invi.pages.open;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignUpPage {
    private MobileDriver<MobileElement> driver;
    private WebDriverWait wait;


    public SignUpPage() {
    }
    public SignUpPage(MobileDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,20);

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(className = "UIAKeyboard")
    private MobileElement keyboard;

    @AndroidFindBy(id = "etEmail")
    private MobileElement emailInput;

    @AndroidFindBy(id = "etPassword")
    private MobileElement passwordInput;

    @AndroidFindBy(id = "btnSignUp")
    private MobileElement signUpButton;

    @AndroidFindBy(id = "textinput_error")
    private MobileElement errorInputLabel;

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

    public MobileElement getErrorInputLabel() {
        return errorInputLabel;
    }
}
