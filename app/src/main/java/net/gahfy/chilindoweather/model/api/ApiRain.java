package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_RAIN_LAST_3_HOURS_VOLUME;

/**
 * Rain as it is returned by the OpenWeatherMap API
 */
public class ApiRain {
    /**
     * Rain volume for the last 3 hours in mm
     */
    @Json(name = JSON_RAIN_LAST_3_HOURS_VOLUME)
    @Nullable
    private Double last3hoursVolume;

    /**
     * Returns the rain volume for the last 3 hours in mm.
     *
     * @return the rain volume for the last 3 hours in mm
     */
    @Nullable
    public Double getLast3hoursVolume() {
        return last3hoursVolume;
    }
}
