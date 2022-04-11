package invi.pages.open;

import invi.pages.BasePage;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsPage extends BasePage {

    public SettingsPage(MobileDriver<MobileElement> driver) {
        super(driver);
    }

    @AndroidFindBy(id = "tvEmail")
    private MobileElement emailLabel;

    @AndroidFindBy(id = "settingsLogout")
    private MobileElement logOutButton;

    public String getEmail() {
        return emailLabel.getText();
    }
}
