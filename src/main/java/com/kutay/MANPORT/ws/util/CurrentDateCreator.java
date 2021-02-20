package com.kutay.MANPORT.ws.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class CurrentDateCreator {
    private static  final String datePattern = "yyyy-MM-dd";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(datePattern);

    public static String getDatePattern() {
        return datePattern;
    }

    public static SimpleDateFormat getFormatter() {
        return formatter;
    }

    public static String currentDateAsString() {
        Date date = currentDateAsDate();
        String currentDateAsAString = formatter.format(date);
        return currentDateAsAString;
    }

    public static Date currentDateAsDate() {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }
}
