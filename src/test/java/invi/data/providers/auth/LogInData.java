package invi.data.providers.auth;

import invi.beans.Register;
import invi.data.providers.TestDataProvider;
import invi.http.RegisterCaller;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

public class LogInData extends TestDataProvider {
    private final Logger LOGGER = Logger.getLogger(LogInData.class.getName());

    @DataProvider(name = "logInData")
    public Object[][] provideData() {
        String currentDate = this.dateUtils.getCurrentDate();
        String incorrectEmail = "incorrect.email";
        String incorrectPassword = "zaqwsx";
        String email = String.format("appium%s@maildrop.cc", currentDate);
        String password = this.propertiesHandler.getProperty("config.properties", "invi.accounts.password");
        String deviceId = String.format("device%s", currentDate);

        RegisterCaller registerCaller = new RegisterCaller();
        HttpResponse<String> registerResponse = null;
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

        LOGGER.info(this.getClass().getName() + "user register status code: " + registerResponse.statusCode());

        return new Object[][]{new Object[]{email, password, incorrectEmail, incorrectPassword}};
    }
}
