package net.gahfy.chilindoweather.utils;

import android.support.annotation.NonNull;

import timber.log.Timber;

public final class Logger {
    private Logger() {
    }

    public static void d(@NonNull final String message) {
        Timber.d(message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public static void i(@NonNull final String message) {
        Timber.i(message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("SameParameterValue")
    public static void w(@NonNull final String message) {
        Timber.w(message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public static void e(@NonNull final String message) {
        Timber.e(message);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public static void e(@NonNull final Throwable tr) {
        Timber.e(tr);
    }
}
