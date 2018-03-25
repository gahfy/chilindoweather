package net.gahfy.chilindoweather.utils;

import android.support.annotation.NonNull;
import android.util.Log;

public final class Logger {
    private Logger() {
    }

    public static void v(@NonNull final String tag, @NonNull final String message) {
        Log.v(tag, message);
    }

    public static void d(@NonNull final String tag, @NonNull final String message) {
        Log.d(tag, message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public static void i(@NonNull final String tag, @NonNull final String message) {
        Log.i(tag, message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("SameParameterValue")
    public static void w(@NonNull final String tag, @NonNull final String message) {
        Log.w(tag, message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public static void e(@NonNull final String tag, @NonNull final String message) {
        Log.e(tag, message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public static void e(@NonNull final String tag, @NonNull Throwable tr) {
        Log.e(tag, "Exception occured", tr);
    }
}
