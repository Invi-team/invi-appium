package invi.beans;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Builder;

@Builder
public class Register implements DataBean {
    private String deviceId;
    private String email;
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userId;

    @JsonGetter("deviceId")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonSetter("deviceId")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    @JsonSetter("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonGetter("userId")
    public String getUserId() {
        return userId;
    }

    @JsonSetter("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
