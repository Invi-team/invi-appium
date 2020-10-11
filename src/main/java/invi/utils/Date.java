package invi.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {
    public static String getDateToFileName() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String dateString = formatter.format(calendar.getTime());
        return dateString.replaceAll(" ", "_");
    }
}
