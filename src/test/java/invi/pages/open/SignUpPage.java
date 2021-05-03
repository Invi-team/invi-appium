package invi.pages.open;

import invi.pages.BasePage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SignUpPage extends BasePage {
    private MobileDriver<MobileElement> driver;
    private WebDriverWait wait;

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

    public SignUpPage(MobileDriver<MobileElement> driver) {
        super(driver);
    }

    public void signUp(String email, String password) {
        hideKeyboardIfVisible(keyboard);
        type(emailInput, email);
        type(passwordInput, password);
        tapOn(signUpButton);
    }

    public MobileElement getErrorInputLabel() {
        return errorInputLabel;
    }
}
