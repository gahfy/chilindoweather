package net.gahfy.chilindoweather.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Locale;

public final class ChilindoWeatherContextWrapper extends ContextWrapper {

    private ChilindoWeatherContextWrapper(@NonNull Context base) {
        super(base);
    }

    @NonNull
    public static ContextWrapper wrap(@NonNull Context context, final String language) {
        Configuration config = context.getResources().getConfiguration();
        Locale sysLocale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = getSystemLocale(config);
        } else {
            sysLocale = getSystemLocaleLegacy(config);
        }
        if (!language.equals("") && !sysLocale.getLanguage().equals(language)) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setSystemLocale(config, locale);
            } else {
                setSystemLocaleLegacy(config, locale);
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(config);
            } else {
                updateConfituration(context, config);
            }
        }
        return new ChilindoWeatherContextWrapper(context);
    }

    @NonNull
    // Safe as we use it only with version < N
    @SuppressWarnings({"deprecation", "squid:CallToDeprecatedMethod"})
    private static Locale getSystemLocaleLegacy(@NonNull final Configuration config) {
        return config.locale;
    }

    // Safe as we use it only with version < JELLY_BEAN_MRA
    @SuppressWarnings({"deprecation", "squid:CallToDeprecatedMethod"})
    private static void updateConfituration(@NonNull final Context context, @NonNull final Configuration config) {
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    @NonNull
    @TargetApi(Build.VERSION_CODES.N)
    private static Locale getSystemLocale(@NonNull final Configuration config) {
        return config.getLocales().get(0);
    }

    // Safe as we use it only with version < N
    @SuppressWarnings({"deprecation", "squid:CallToDeprecatedMethod"})
    private static void setSystemLocaleLegacy(@NonNull final Configuration config, @NonNull final Locale locale) {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static void setSystemLocale(@NonNull final Configuration config, @NonNull final Locale locale) {
        config.setLocale(locale);
    }
}
