package invi.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public interface Device {
    public final static String APK_PATH = "Build Artifacts/apk/debug/app-debug.apk";
    public DesiredCapabilities getDc();
}
