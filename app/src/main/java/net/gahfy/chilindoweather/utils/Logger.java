package net.gahfy.chilindoweather.utils;

import android.util.Log;

import timber.log.Timber;

public class Logger {
    private Logger() {
    }

    public static void d(String tag, String message) {
        Timber.d(tag, message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public static void i(String tag, String message) {
        Timber.i(tag, message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("SameParameterValue")
    public static void w(String tag, String message) {
        Timber.w(tag, message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public static void e(String tag, String message) {
        Timber.e(tag, message);
    }

    public static void e(String tag, Throwable tr) {
        Timber.e(tag, Log.getStackTraceString(tr));
    }
}
