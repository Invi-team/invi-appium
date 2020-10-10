package invi.pages.guest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import invi.utils.Waiter;



public class MainPage {
    private AndroidDriver<AndroidElement> driver;
    private WebDriverWait wait;


    public MainPage() {
    }
    public MainPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,20);

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = "@id/ivLogout")
    private AndroidElement logOutButton;

    @AndroidFindBy(id = "@android:id/button1")
    private AndroidElement confirmLogOutButton;

    @AndroidFindBy(id = "@android:id/button2")
    private AndroidElement cancelLogOutButton;

    public AndroidElement getLogOutButton() {
        return logOutButton;
    }

    public void clickLogOut() {
        wait.until(ExpectedConditions.visibilityOf(this.logOutButton));
        logOutButton.click();
    }

    public void confirmLogOut() {
        wait.until(ExpectedConditions.visibilityOf(this.confirmLogOutButton));
        confirmLogOutButton.click();
    }

    public void logOut() {
        clickLogOut();
        confirmLogOut();
    }
}
