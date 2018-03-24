package net.gahfy.chilindoweather.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

public class ChilindoWeatherContextWrapper extends ContextWrapper {

    public ChilindoWeatherContextWrapper(Context base) {
        super(base);
    }

    @SuppressWarnings("deprecation")
    public static ContextWrapper wrap(Context context, String language) {
        Configuration config = context.getResources().getConfiguration();
        Locale sysLocale = null;
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

    // Safe as we use it only with version < N
    @SuppressWarnings({"deprecation", "squid:CallToDeprecatedMethod"})
    public static Locale getSystemLocaleLegacy(Configuration config) {
        return config.locale;
    }

    // Safe as we use it only with version < JELLY_BEAN_MRA
    @SuppressWarnings({"deprecation", "squid:CallToDeprecatedMethod"})
    public static void updateConfituration(Context context, Configuration config) {
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getSystemLocale(Configuration config) {
        return config.getLocales().get(0);
    }

    // Safe as we use it only with version < N
    @SuppressWarnings({"deprecation", "squid:CallToDeprecatedMethod"})
    public static void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }
}
