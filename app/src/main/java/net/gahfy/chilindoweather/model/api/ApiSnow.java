package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_SNOW_LAST_3_HOURS_VOLUME;

/**
 * Snow as it is returned by the OpenWeatherMap API
 * @version 2.5
 */
public final class ApiSnow {
    /**
     * Snow volume for the last 3 hours in mm
     */
    @Json(name = JSON_SNOW_LAST_3_HOURS_VOLUME)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double last3hoursVolume;

    /**
     * Returns the snow volume for the last 3 hours in mm.
     *
     * @return the snow volume for the last 3 hours in mm
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getLast3hoursVolume() {
        return last3hoursVolume;
    }
}
