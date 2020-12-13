package invi.pages.open;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LandingPage {
    private MobileDriver<MobileElement> driver;
    private WebDriverWait wait;

    @AndroidFindBy(id = "com.kiksoft.invi:id/btnSignIn")
    private MobileElement signInLinkButton;

    @AndroidFindBy(id = "com.kiksoft.invi:id/tvSignUpPageButton")
    private MobileElement signUpLinkButton;

    public LandingPage(MobileDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,20);
        PageFactory.initElements(new AppiumFieldDecorator(driver) ,this);
    }

    public void selectSignUp() {
        this.wait.until(ExpectedConditions.visibilityOf(this.signUpLinkButton));
        this.signUpLinkButton.click();
    }

    public void selectSignIn() {
        this.wait.until(ExpectedConditions.visibilityOf(this.signInLinkButton));
        this.signInLinkButton.click();
    }
}
