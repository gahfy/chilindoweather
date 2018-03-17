package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WIND_DIRECTION;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WIND_SPEED;

/**
 * Wind as it is returned by the OpenWeatherMap API.
 */
public class ApiWind {
    /**
     * Wind speed in meter/sec
     */
    @Json(name = JSON_WIND_SPEED)
    @Nullable
    private Double speed;

    /**
     * Wind direction in degrees from the North
     */
    @Json(name = JSON_WIND_DIRECTION)
    @Nullable
    private Double direction;

    /**
     * Returns the wind speed in meter/sec.
     *
     * @return the wind speed in meter/sec
     */
    @Nullable
    public Double getSpeed() {
        return speed;
    }

    /**
     * Returns the wind direction in degrees from the North.
     *
     * @return the wind direction in degrees from the North
     */
    @Nullable
    public Double getDirection() {
        return direction;
    }
}
