package net.gahfy.chilindoweather.utils;

import android.util.Log;

public class Logger {
    private Logger() {
    }

    public static int d(String tag, String message) {
        return Log.d(tag, message);
    }

    public static int i(String tag, String message) {
        return Log.i(tag, message);
    }

    public static int w(String tag, String message) {
        return Log.w(tag, message);
    }

    public static void e(String tag, String message) {
        Log.e(tag, message);
    }

    public static void e(String tag, Throwable tr) {
        Log.e(tag, Log.getStackTraceString(tr));
    }
}
