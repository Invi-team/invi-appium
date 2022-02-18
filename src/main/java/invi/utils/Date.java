package invi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
    private final Calendar calendar = Calendar.getInstance();

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss-SSS");

        return formatter.format(calendar.getTime());
    }
}
