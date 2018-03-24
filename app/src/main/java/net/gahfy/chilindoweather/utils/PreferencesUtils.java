package net.gahfy.chilindoweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

public final class PreferencesUtils {
    private static final String PREFERENCE_TEMPERATURE = "temperature";
    private static final String PREFERENCE_WIND_SPEED = "wind_speed";

    private final SharedPreferences sharedPreferences;

    public PreferencesUtils(@NonNull final Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public final int getTemperatureIndex() {
        return sharedPreferences.getInt(PREFERENCE_TEMPERATURE, UnitUtils.getDefaultTemperatureIndex());
    }

    public final void setTemperatureIndex(final int temperatureIndex) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFERENCE_TEMPERATURE, temperatureIndex); // value to store
        editor.apply();
    }

    public final int getWindSpeedIndex() {
        return sharedPreferences.getInt(PREFERENCE_WIND_SPEED, UnitUtils.getDefaultWindSpeedIndex());
    }

    public final void setWindSpeedIndex(final int windSpeedIndex) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PREFERENCE_WIND_SPEED, windSpeedIndex); // value to store
        editor.apply();
    }
}
