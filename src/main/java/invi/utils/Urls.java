package invi.utils;

public class Urls {
    private final static String BASE_URL = new PropertiesHandler().getProperty("config.properties", "invi.api.baseurl");


    public static final String register() {
        return BASE_URL + "/register";
    }

    public static final String login() {
        return BASE_URL + "/auth/login";
    }

    public static final String createEvent() {
        return BASE_URL + "/events";
    }

    public static final String updateEventDetails(String eventId) {
        return String.format(BASE_URL + "/event/%s", eventId);
    }
}
