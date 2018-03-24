package net.gahfy.chilindoweather.utils;


import android.os.Handler;
import android.support.annotation.NonNull;

public class HandlerUtils {
    private HandlerUtils() {
    }

    // Safe as this is a utility method
    @SuppressWarnings("SameParameterValue")
    public static void postDelayed(@NonNull final Runnable runnable, final int millisecondsDelay) {
        new Handler().postDelayed(runnable, millisecondsDelay);
    }
}
