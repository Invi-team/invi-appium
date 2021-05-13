package invi.pages.open;

import invi.pages.BasePage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LandingPage extends BasePage {

    @AndroidFindBy(id = "com.kiksoft.invi:id/btnSignIn")
    private MobileElement signInLinkButton;

    @AndroidFindBy(id = "com.kiksoft.invi:id/tvSignUpPageButton")
    private MobileElement signUpLinkButton;

    public LandingPage(MobileDriver<MobileElement> driver) {
        super(driver);
    }

    public void selectSignUp() {
        tapPoint(signUpLinkButton, 90, 50);
    }

    public void selectSignIn() {
        tapOn(signInLinkButton);
    }
}
