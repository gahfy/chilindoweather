package net.gahfy.chilindoweather.utils;

import java.util.Calendar;

public class DateUtils {
    public static Integer getMidnightTimestamp(Integer timestamp) {
        if (timestamp != null) {
            long javaTimestamp = timestamp.longValue() * 1000L;
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(javaTimestamp);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            return Long.valueOf(c.getTimeInMillis() / 1000L).intValue();
        }
        return null;
    }
}
