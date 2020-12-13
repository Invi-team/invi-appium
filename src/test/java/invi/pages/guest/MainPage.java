package invi.pages.guest;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {
    private MobileDriver<MobileElement> driver;
    private WebDriverWait wait;

    @AndroidFindBy(id = "com.kiksoft.invi:id/ivLogout")
    private MobileElement logOutButton;

    @AndroidFindBy(id = "@android:id/button1")
    private MobileElement confirmLogOutButton;

    @AndroidFindBy(id = "@android:id/button2")
    private MobileElement cancelLogOutButton;

    public MainPage(MobileDriver<MobileElement> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver,20);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public MobileElement getLogOutButton() {
        return this.logOutButton;
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
