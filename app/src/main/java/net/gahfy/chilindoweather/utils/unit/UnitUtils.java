package net.gahfy.chilindoweather.utils.unit;

import java.util.Locale;

public class UnitUtils {
    public static final int CELSIUS_INDEX = 0;
    public static final int FAHRENHEIT_INDEX = 1;

    public static final int METERS_INDEX = 0;
    public static final int MILES_INDEX = 1;

    public static int getDefaultTemperatureIndex() {
        Locale defaultLocale = Locale.getDefault();
        // Fahrenheit is the default temperature unit for United States, Cayman Islands and Belize
        if (defaultLocale.getCountry().equals("US") || defaultLocale.getCountry().equals("KY") || defaultLocale.getCountry().equals("BZ")) {
            return FAHRENHEIT_INDEX;
        }
        return CELSIUS_INDEX;
    }

    public static int getDefaultWindSpeedIndex() {
        Locale defaultLocale = Locale.getDefault();
        // Miles is the default distance unit for United States, Liberia and Myanmar
        if (defaultLocale.getCountry().equals("US") || defaultLocale.getCountry().equals("LR") || defaultLocale.getCountry().equals("MM")) {
            return MILES_INDEX;
        }
        return METERS_INDEX;
    }
}
