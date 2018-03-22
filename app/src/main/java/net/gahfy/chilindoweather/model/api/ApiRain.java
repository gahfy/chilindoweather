package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_RAIN_LAST_3_HOURS_VOLUME;

/**
 * Rain as it is returned by the OpenWeatherMap API
 * @version 2.5
 */
// Safe as we want API POJOs to be specific (in case API changes) instead of using inheritance
@SuppressWarnings("common-java:DuplicatedBlocks")
public final class ApiRain {
    /**
     * Rain volume for the last 3 hours in mm
     */
    @Json(name = JSON_RAIN_LAST_3_HOURS_VOLUME)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double last3hoursVolume;

    /**
     * Returns the rain volume for the last 3 hours in mm.
     *
     * @return the rain volume for the last 3 hours in mm
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getLast3hoursVolume() {
        return last3hoursVolume;
    }
}
