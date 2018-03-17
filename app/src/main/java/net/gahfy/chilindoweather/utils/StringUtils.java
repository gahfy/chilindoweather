package net.gahfy.chilindoweather.utils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils {
    @NonNull
    public static String formatWeatherCalculationDate(String language, String dateFormatString, int timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString, new Locale(language));
        Date dateInstance = new Date(1000L * ((long) timestamp));
        return dateFormat.format(dateInstance);
    }
}
