package net.gahfy.chilindoweather.utils;


import android.os.Handler;

public class HandlerUtils {
    private HandlerUtils() {
    }

    public static void postDelayed(Runnable runnable, int millisecondsDelay) {
        new Handler().postDelayed(runnable, millisecondsDelay);
    }
}
