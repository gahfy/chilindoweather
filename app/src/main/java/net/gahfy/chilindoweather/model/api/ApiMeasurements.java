package net.gahfy.chilindoweather.model.api;


import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_MEASUREMENTS_GROUND_LEVEL_PRESSURE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_MEASUREMENTS_HUMIDITY;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_MEASUREMENTS_MAXIMUM_TEMPERATURE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_MEASUREMENTS_MINIMUM_TEMPERATURE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_MEASUREMENTS_PRESSURE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_MEASUREMENTS_SEA_LEVEL_PRESSURE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_MEASUREMENTS_TEMPERATURE;

/**
 * Weather measurements as they are returned by the OpenWeatherMap API
 */
public final class ApiMeasurements {
    /**
     * Temperature in Kelvin
     */
    @Json(name = JSON_MEASUREMENTS_TEMPERATURE)
    @Nullable
    private Double temperature;

    /**
     * Atmospheric pressure (sea level, if there is no sea or ground level data) in hPa
     */
    @Json(name = JSON_MEASUREMENTS_PRESSURE)
    @Nullable
    private Double pressure;

    /**
     * Humidity in percent
     */
    @Json(name = JSON_MEASUREMENTS_HUMIDITY)
    @Nullable
    private Integer humidity;

    /**
     * Minimum temperature in Kelvin at the moment (possible for large areas)
     */
    @Json(name = JSON_MEASUREMENTS_MINIMUM_TEMPERATURE)
    @Nullable
    private Double minimumTemperature;

    /**
     * Maximum temperature in Kelvin at the moment (possible for large areas)
     */
    @Json(name = JSON_MEASUREMENTS_MAXIMUM_TEMPERATURE)
    @Nullable
    private Double maximumTemperature;

    /**
     * Atmospheric pressure on the sea level in hPa
     */
    @Json(name = JSON_MEASUREMENTS_SEA_LEVEL_PRESSURE)
    @Nullable
    private Double seaLevelPressure;

    /**
     * Atmospheric pressure on the ground level in hPa
     */
    @Json(name = JSON_MEASUREMENTS_GROUND_LEVEL_PRESSURE)
    @Nullable
    private Double groundLevelPressure;

    /**
     * Returns the temperature in Kelvin.
     *
     * @return the temperature in Kelvin
     */
    @Nullable
    public final Double getTemperature() {
        return temperature;
    }

    /**
     * Returns the atmospheric pressure (sea level, if there is no sea or ground level data) in hPa.
     *
     * @return the atmospheric pressure (sea level, if there is no sea or ground level data) in hPa
     */
    @Nullable
    public final Double getPressure() {
        return pressure;
    }

    /**
     * Returns the humidity in percent.
     *
     * @return the humidity in percent
     */
    @Nullable
    public final Integer getHumidity() {
        return humidity;
    }

    /**
     * Returns the minimum temperature in Kelvin at the moment (possible for large areas).
     *
     * @return the minimum temperature in Kelvin at the moment (possible for large areas)
     */
    @Nullable
    public final Double getMinimumTemperature() {
        return minimumTemperature;
    }

    /**
     * Returns the maximum temperature in Kelvin at the moment (possible for large areas).
     *
     * @return the maximum temperature in Kelvin at the moment (possible for large areas)
     */
    @Nullable
    public final Double getMaximumTemperature() {
        return maximumTemperature;
    }

    /**
     * Returns the atmospheric pressure on the sea level in hPa.
     *
     * @return the atmospheric pressure on the sea level in hPa
     */
    @Nullable
    public final Double getSeaLevelPressure() {
        return seaLevelPressure;
    }

    /**
     * Returns the atmospheric pressure on the ground level in hPa.
     *
     * @return the atmospheric pressure on the ground level in hPa
     */
    @Nullable
    public final Double getGroundLevelPressure() {
        return groundLevelPressure;
    }
}
