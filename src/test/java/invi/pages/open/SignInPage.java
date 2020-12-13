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

    public SignInPage(MobileDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,20);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void hideKeyboardIfVisible() {
        if (this.keyboard != null) {
            this.driver.hideKeyboard();
        }
    }

    public void typeEmail(String email) {
        this.wait.until(ExpectedConditions.visibilityOf(this.emailInput));
        this.emailInput.clear();
        this.emailInput.sendKeys(email);
    }

    public void typePassword(String password) {
        this.wait.until(ExpectedConditions.visibilityOf(this.passwordInput));
        this.passwordInput.clear();
        this.passwordInput.sendKeys(password);
    }

    public void clickSignIn() {
        this.wait.until(ExpectedConditions.visibilityOf(this.signInButton));
        this.signInButton.click();
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
