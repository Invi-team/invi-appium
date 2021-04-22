package invi.data.providers.auth;

import invi.data.providers.TestDataProvider;
import org.testng.annotations.DataProvider;

import java.util.logging.Logger;

public class SignUpData extends TestDataProvider {
    private final Logger LOGGER = Logger.getLogger(LogInData.class.getName());

    @DataProvider(name = "signUpData")
    public Object[][] provideData() {
        String currentDate = this.dateUtils.getCurrentDate();
        String incorrectEmail = "incorrect.email";
        String incorrectPassword = "zaqwsx";
        String email = String.format("appium%s@maildrop.cc", currentDate);
        String password = this.propertiesHandler.getProperty("config.properties", "invi.accounts.password");

        return new Object[][]{new Object[]{email, password, incorrectEmail, incorrectPassword}};
    }
}
