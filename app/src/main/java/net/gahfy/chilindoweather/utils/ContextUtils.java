package net.gahfy.chilindoweather.utils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Locale;

public final class ContextUtils {
    private ContextUtils() {
    }

    @NonNull
    public static Locale getLocale(@NonNull final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            return getDeprecatedLocale(context);
        }
    }

    @NonNull
    // Safe as we use it only with version < N
    @SuppressWarnings({"deprecation", "squid:CallToDeprecatedMethod"})
    private static Locale getDeprecatedLocale(@NonNull final Context context) {
        return context.getResources().getConfiguration().locale;
    }
}
