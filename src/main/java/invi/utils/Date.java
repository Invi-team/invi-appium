package invi.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {
    public String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss-SSS");
        return formatter.format(LocalDateTime.now());
    }
}
