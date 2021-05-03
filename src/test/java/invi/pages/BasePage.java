package invi.pages;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public abstract class BasePage {
    MobileDriver<MobileElement> driver;
    public WebDriverWait wait;

    public BasePage(MobileDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void hideKeyboardIfVisible(MobileElement keyboard) {
        if (keyboard != null) {
            driver.hideKeyboard();
        }
    }

    public void type(MobileElement element, String value) {
        wait.until(visibilityOf(element));
        element.clear();
        element.sendKeys(value);
    }

    public void tapOn(MobileElement element) {
        wait.until(visibilityOf(element));
        element.click();
    }
}
