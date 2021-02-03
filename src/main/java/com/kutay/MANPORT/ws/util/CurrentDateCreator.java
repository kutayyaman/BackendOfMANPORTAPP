package com.kutay.MANPORT.ws.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class CurrentDateCreator {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    public static String currentDateAsString() {
        Date date = currentDateAsDate(formatter);
        String currentDateAsAString = formatter.format(date);
        return currentDateAsAString;
    }

    public static Date currentDateAsDate(SimpleDateFormat simpleFormatter) {
        Date date = new Date(System.currentTimeMillis());
        return date;
    }
}
