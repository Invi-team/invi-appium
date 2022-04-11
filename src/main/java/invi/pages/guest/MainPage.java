package invi.pages.guest;

import invi.pages.BasePage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MainPage extends BasePage {

    @AndroidFindBy(id = "com.kiksoft.invi:id/ivSettings")
    private MobileElement settingsButton;

    public MainPage(MobileDriver<MobileElement> driver) {
        super(driver);
    }

    public void navigateToSettings() {
        tapOn(settingsButton);
    }
}
