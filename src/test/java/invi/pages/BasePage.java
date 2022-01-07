package invi.pages;

import invi.utils.System;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

import static io.appium.java_client.touch.offset.PointOption.point;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public abstract class BasePage {
    public MobileDriver<MobileElement> driver;
    public WebDriverWait wait;
    public TouchAction touchAction;
    private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());


    public BasePage(MobileDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);

        if (System.ANDROID.isActive) {
            touchAction = new AndroidTouchAction(driver);
        } else if (System.IOS.isActive) {
            touchAction = new IOSTouchAction(driver);
        }
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

    /*
    Method percentage arguments are used to select point to tap on an element
    So e.g. if You want to click on point at 0.9 length and at 0.5 height of the element,
    You have to pass 90 and 50 as arguments.
    */
    public void tapPoint(MobileElement element, int percentageX, int percentageY) {
        wait.until(elementToBeClickable(element));

        Point location = element.getLocation();
        Dimension size = element.getSize();
        int relativeX = Math.round(size.width * percentageX / 100);
        int relativeY = Math.round(size.height * percentageY / 100);
        int pointX = (location.x + relativeX);
        int pointY = (location.y + relativeY);

        touchAction.tap(point(pointX, pointY)).perform();
    }
}
