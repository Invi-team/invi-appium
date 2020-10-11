package invi.pages.open;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignInPage {
    private MobileDriver<MobileElement> driver;
    private WebDriverWait wait;


    public SignInPage() {
    }
    public SignInPage(MobileDriver<MobileElement> driver) {
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

    @AndroidFindBy(id = "com.kiksoft.invi:id/btnSignUp")
    private MobileElement signInButton;

    @AndroidFindBy(id = "com.kiksoft.invi:id/textinput_error")
    private MobileElement emailInputErrorLabel;

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

    public void clickSignIn() {
        wait.until(ExpectedConditions.visibilityOf(this.signInButton));
        signInButton.click();
    }

    public void signIn(String email, String password) {
        hideKeyboardIfVisible();
        typeEmail(email);
        typePassword(password);
        clickSignIn();
    }

    public MobileElement getEmailInputErrorLabel() {
        return emailInputErrorLabel;
    }
}
