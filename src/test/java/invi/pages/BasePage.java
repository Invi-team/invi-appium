package invi.pages;

import invi.utils.PropertiesHandler;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.appium.java_client.touch.offset.PointOption.point;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public abstract class BasePage {
    private static final String SYSTEM_ANDROID = "Android";
    private static final String SYSTEM_IOS = "iOS";
    public MobileDriver<MobileElement> driver;
    public WebDriverWait wait;
    public TouchAction touchAction;
    public String mobileSystem;

    public BasePage(MobileDriver<MobileElement> driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        mobileSystem = new PropertiesHandler().getProperty("config.properties", "device.system");

        //if (mobileSystem.equals(SYSTEM_ANDROID)) {
            touchAction = new AndroidTouchAction(driver);
        //} else if (mobileSystem.equals(SYSTEM_IOS)) {
        //    touchAction = new IOSTouchAction(driver);
        //}
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

    public void tapPoint(MobileElement element, int x, int y){
        wait.until(visibilityOf(element));
        Point location = element.getLocation();
        Point center = element.getCenter();
        int pointX = location.x + x;
        int pointY = location.y + y;

        touchAction.tap(point(pointX, pointY)).perform();
    }
}
