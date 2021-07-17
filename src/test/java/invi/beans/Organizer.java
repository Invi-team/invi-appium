package invi.beans;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Builder;

@Builder
public class Organizer implements DataBean {
    private String id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String type;

    @JsonGetter("id")
    public String getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonGetter("surname")
    public String getSurname() {
        return surname;
    }

    @JsonSetter("surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonGetter("phoneNumber")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @JsonGetter("phoneNumber")
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonGetter("type")
    public String getType() {
        return type;
    }

    @JsonSetter("type")
    public void setType(String type) {
        this.type = type;
    }
}
