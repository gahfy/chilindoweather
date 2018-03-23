package net.gahfy.chilindoweather.utils;

import android.support.annotation.Nullable;

public final class IntegerUtils {
    private IntegerUtils() {
    }

    public static boolean equals(@Nullable final Integer int1, @Nullable final Integer int2) {
        if (int1 == null) {
            return int2 == null;
        }
        return int1.equals(int2);
    }
}
