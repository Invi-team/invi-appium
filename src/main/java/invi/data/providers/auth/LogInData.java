package invi.data.providers.auth;

import invi.beans.Register;
import invi.data.providers.TestDataProvider;
import invi.http.RegisterCaller;
import okhttp3.Response;
import org.testng.annotations.DataProvider;

import java.io.IOException;
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

        RegisterCaller registerCaller = new RegisterCaller();
        Response registerResponse = null;
        Register registerBean = Register.builder()
                .deviceId(deviceId)
                .email(email)
                .password(password)
                .build();

        try {
            registerResponse = registerCaller.call(registerBean);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.throwing(LogInData.class.getName(), "provideData", e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LOGGER.throwing(LogInData.class.getName(), "provideData", e);
        }

        LOGGER.info(LogInData.class.getName() + " user register status code: " + registerResponse.code());

        return new Object[][]{new Object[]{email, password, incorrectEmail, incorrectPassword}};
    }
}
