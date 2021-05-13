package invi.pages.guest;

import invi.pages.BasePage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainPage extends BasePage {

    @AndroidFindBy(id = "com.kiksoft.invi:id/ivLogout")
    private MobileElement logOutButton;

    @AndroidFindBy(id = "@android:id/button1")
    private MobileElement confirmLogOutButton;

    @AndroidFindBy(id = "@android:id/button2")
    private MobileElement cancelLogOutButton;

    public MainPage(MobileDriver<MobileElement> driver) {
        super(driver);
    }

    public MobileElement getLogOutButton() {
        return logOutButton;
    }

    public void logOut() {
        tapOn(logOutButton);
        tapOn(confirmLogOutButton);
    }
}
