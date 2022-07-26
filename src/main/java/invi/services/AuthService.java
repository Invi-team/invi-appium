package invi.services;

import invi.beans.Register;
import invi.http.RegisterCaller;
import okhttp3.Response;

import java.io.IOException;
import java.util.logging.Logger;

public class AuthService implements BaseService {
    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());
    private String email;
    private String password;
    private String deviceId;


    public void register(String email, String password, String deviceId) {
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public AuthService login(String email, String password) {
        return this;
    }

}
