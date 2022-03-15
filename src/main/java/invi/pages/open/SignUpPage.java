package invi.pages.open;

import invi.pages.BasePage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SignUpPage extends BasePage {

    @AndroidFindBy(className = "UIAKeyboard")
    private MobileElement keyboard;

    @AndroidFindBy(id = "etEmail")
    private MobileElement emailInput;

    @AndroidFindBy(id = "etPassword")
    private MobileElement passwordInput;

    @AndroidFindBy(id = "etPasswordRepeat")
    private MobileElement repeatPasswordInput;

    @AndroidFindBy(id = "btnSignUp")
    private MobileElement signUpButton;

    @AndroidFindBy(id = "com.kiksoft.invi:id/textinput_error")
    private MobileElement errorInputLabel;

    public SignUpPage(MobileDriver<MobileElement> driver) {
        super(driver);
    }

    public void signUp(String email, String password) {
        hideKeyboardIfVisible(keyboard);
        type(emailInput, email);
        type(passwordInput, password);
        type(repeatPasswordInput, password);
        tapOn(signUpButton);
    }

    public MobileElement getErrorInputLabel() {
        wait.until(visibilityOf(errorInputLabel));
        return errorInputLabel;
    }
}
