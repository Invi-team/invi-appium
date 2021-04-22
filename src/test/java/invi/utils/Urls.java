package invi.utils;

public class Urls {
    private String BASE_URL = new PropertiesHandler().getProperty("config.properties", "invi.api.baseurl");


    public String register() {
        return BASE_URL + "/register";
    }

    public String login() {
        return BASE_URL + "/auth/login";
    }

    public String createEvent() {
        return BASE_URL + "/events";
    }

    public String updateEventDetails(String eventId) {
        return String.format(BASE_URL + "/event/%s", eventId);
    }
}
