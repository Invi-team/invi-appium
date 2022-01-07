package invi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
    private Calendar calendar = Calendar.getInstance();

    public String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
        String dateString = formatter.format(calendar.getTime());

        return dateString
                .replaceAll(" ", "_")
                .replaceAll(":", "-")
                .replaceAll("\\.", "-");
    }
}
