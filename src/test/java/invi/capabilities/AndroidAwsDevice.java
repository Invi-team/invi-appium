package invi.capabilities;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AndroidAwsDevice implements Device {
    private DesiredCapabilities dc = new DesiredCapabilities();

    public AndroidAwsDevice() {
    }

    public DesiredCapabilities getDc() {
        return dc;
    }
}
