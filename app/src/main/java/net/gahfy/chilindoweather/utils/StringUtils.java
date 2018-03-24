package net.gahfy.chilindoweather.utils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils {
    private static final long JAVA_TIMESTAMP_MULTIPLIER = 1000L;

    private StringUtils() {
    }

    @NonNull
    public static String formatDate(@NonNull final Locale locale, @NonNull final String dateFormatString, @NonNull final int timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatString, locale);
        Date dateInstance = new Date(JAVA_TIMESTAMP_MULTIPLIER * ((long) timestamp));
        return dateFormat.format(dateInstance);
    }

    @NonNull
    public static String formatDateWithLongWeekDay(Locale locale, String dateFormatString, int timestamp, String[] weekDays, String[] months) {
        SimpleDateFormat dataDateFormat = new SimpleDateFormat("u/M", Locale.US);
        Date dateInstance = new Date(1000L * ((long) timestamp));
        String[] dateData = dataDateFormat.format(dateInstance).split("/");
        int weekDay = Integer.parseInt(dateData[0]) - 1;
        int month = Integer.parseInt(dateData[1]) - 1;
        dateFormatString = dateFormatString.replaceAll("\\{wd\\}", "'".concat(weekDays[weekDay]).concat("'"));
        dateFormatString = dateFormatString.replaceAll("\\{mn\\}", "'".concat(months[month]).concat("'"));
        return formatDate(locale, dateFormatString, timestamp);
    }
}
