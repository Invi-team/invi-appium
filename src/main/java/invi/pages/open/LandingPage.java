package invi.pages.open;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import invi.utils.Waiter;


public class LandingPage {
    private AndroidDriver<AndroidElement> driver;
    private WebDriverWait wait;

    public LandingPage() {
    }

    public LandingPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,20);
        PageFactory.initElements(new AppiumFieldDecorator(driver) ,this);
    }

    @AndroidFindBy(id = "com.kiksoft.invi:id/btnSignIn")
    private AndroidElement signInLinkButton;

    @AndroidFindBy(id = "com.kiksoft.invi:id/tvSignUpPageButton")
    private AndroidElement signUpLinkButton;

    public void selectSignUp() {
        wait.until(ExpectedConditions.visibilityOf(this.signUpLinkButton));
        signUpLinkButton.click();
    }

    public void selectSignIn() {
        wait.until(ExpectedConditions.visibilityOf(this.signInLinkButton));
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        signInLinkButton.click();
    }
}
