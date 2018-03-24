package net.gahfy.chilindoweather.utils;

import android.support.annotation.Nullable;

import java.util.Calendar;

public final class DateUtils {
    private DateUtils() {
    }

    @Nullable
    public static Integer getMidnightTimestamp(@Nullable final Integer timestamp) {
        if (timestamp != null) {
            long javaTimestamp = timestamp.longValue() * 1000L;
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(javaTimestamp);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            return (int) (c.getTimeInMillis() / 1000L);
        }
        return null;
    }
}
