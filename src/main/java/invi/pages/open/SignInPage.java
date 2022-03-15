package invi.pages.open;

import invi.pages.BasePage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SignInPage extends BasePage {

    @AndroidFindBy(className = "UIAKeyboard")
    private MobileElement keyboard;

    @AndroidFindBy(id = "com.kiksoft.invi:id/etEmail")
    private MobileElement emailInput;

    @AndroidFindBy(id = "com.kiksoft.invi:id/etPassword")
    private MobileElement passwordInput;

    @AndroidFindBy(id = "com.kiksoft.invi:id/btnSignIn")
    private MobileElement signInButton;

    @AndroidFindBy(id = "com.kiksoft.invi:id/textinput_error")
    private MobileElement emailInputErrorLabel;

    public SignInPage(MobileDriver<MobileElement> driver) {
        super(driver);
    }

    public void signIn(String email, String password) {
        hideKeyboardIfVisible(keyboard);
        type(emailInput, email);
        type(passwordInput, password);
        tapOn(signInButton);
    }

    public MobileElement getEmailInputErrorLabel() {
        wait.until(visibilityOf(emailInputErrorLabel));
        return emailInputErrorLabel;
    }
}
