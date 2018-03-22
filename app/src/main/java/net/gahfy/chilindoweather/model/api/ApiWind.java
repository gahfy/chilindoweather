package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WIND_DIRECTION;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WIND_SPEED;

/**
 * Wind as it is returned by the OpenWeatherMap API.
 * @version 2.5
 */
// Safe as we want API POJOs to be specific (in case API changes) instead of using inheritance
@SuppressWarnings("findbugs:common-java:DuplicatedBlocks")
public final class ApiWind {
    /**
     * Wind speed in meter/sec
     */
    @Json(name = JSON_WIND_SPEED)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double speed;

    /**
     * Wind direction in degrees from the North
     */
    @Json(name = JSON_WIND_DIRECTION)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double direction;

    /**
     * Returns the wind speed in meter/sec.
     *
     * @return the wind speed in meter/sec
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getSpeed() {
        return speed;
    }

    /**
     * Returns the wind direction in degrees from the North.
     *
     * @return the wind direction in degrees from the North
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getDirection() {
        return direction;
    }
}
