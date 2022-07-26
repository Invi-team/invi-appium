package invi.data.providers.auth;

import invi.data.providers.TestDataProvider;
import invi.services.AuthService;
import org.testng.annotations.DataProvider;

import java.util.logging.Logger;

public class LogInData extends TestDataProvider {
    private static final Logger LOGGER = Logger.getLogger(LogInData.class.getName());

    @DataProvider(name = "logInData")
    public static Object[][] provideData() {
        String currentDate = dateUtils.getCurrentDate();
        String incorrectEmail = "incorrect.email";
        String incorrectPassword = "zaqwsx";
        String email = String.format("appium%s@gmail.com", currentDate);
        String password = propertiesHandler.getProperty("config.properties", "invi.accounts.password");
        String deviceId = String.format("device%s", currentDate);
        AuthService authService = new AuthService();

        authService.register(email, password, deviceId);

        return new Object[][]{new Object[]{email, password, incorrectEmail, incorrectPassword}};
    }
}
