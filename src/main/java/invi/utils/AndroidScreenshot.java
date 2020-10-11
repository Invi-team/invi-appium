package invi.utils;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;

import java.io.File;
import java.io.IOException;
import invi.utils.Date;

public class AndroidScreenshot {
    private static AndroidDriver driver;

    public AndroidScreenshot(AndroidDriver driver) {
        this.driver = driver;
    }

    public void takeScreenshot() {
        try {
            File file = this.driver.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("screenshots/screenshot_" + Date.getDateToFileName() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
