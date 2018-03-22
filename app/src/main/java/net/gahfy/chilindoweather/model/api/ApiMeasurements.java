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
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_MEASUREMENTS_TEMP_KF;

/**
 * Weather measurements as they are returned by the OpenWeatherMap API
 * @version 2.5
 */
public final class ApiMeasurements {
    /**
     * Temperature in Kelvin
     */
    @Json(name = JSON_MEASUREMENTS_TEMPERATURE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double temperature;

    /**
     * Atmospheric pressure (sea level, if there is no sea or ground level data) in hPa
     */
    @Json(name = JSON_MEASUREMENTS_PRESSURE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double pressure;

    /**
     * Humidity in percent
     */
    @Json(name = JSON_MEASUREMENTS_HUMIDITY)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer humidity;

    /**
     * Minimum temperature in Kelvin at the moment (possible for large areas)
     */
    @Json(name = JSON_MEASUREMENTS_MINIMUM_TEMPERATURE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double minimumTemperature;

    /**
     * Maximum temperature in Kelvin at the moment (possible for large areas)
     */
    @Json(name = JSON_MEASUREMENTS_MAXIMUM_TEMPERATURE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double maximumTemperature;

    /**
     * Atmospheric pressure on the sea level in hPa
     */
    @Json(name = JSON_MEASUREMENTS_SEA_LEVEL_PRESSURE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double seaLevelPressure;

    /**
     * Atmospheric pressure on the ground level in hPa
     */
    @Json(name = JSON_MEASUREMENTS_GROUND_LEVEL_PRESSURE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double groundLevelPressure;

    /**
     * The temp_kf value
     */
    @Json(name = JSON_MEASUREMENTS_TEMP_KF)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double tempKf;

    /**
     * Returns the temperature in Kelvin.
     *
     * @return the temperature in Kelvin
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getTemperature() {
        return temperature;
    }

    /**
     * Returns the atmospheric pressure (sea level, if there is no sea or ground level data) in hPa.
     *
     * @return the atmospheric pressure (sea level, if there is no sea or ground level data) in hPa
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getPressure() {
        return pressure;
    }

    /**
     * Returns the humidity in percent.
     *
     * @return the humidity in percent
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getHumidity() {
        return humidity;
    }

    /**
     * Returns the minimum temperature in Kelvin at the moment (possible for large areas).
     *
     * @return the minimum temperature in Kelvin at the moment (possible for large areas)
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getMinimumTemperature() {
        return minimumTemperature;
    }

    /**
     * Returns the maximum temperature in Kelvin at the moment (possible for large areas).
     *
     * @return the maximum temperature in Kelvin at the moment (possible for large areas)
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getMaximumTemperature() {
        return maximumTemperature;
    }

    /**
     * Returns the atmospheric pressure on the sea level in hPa.
     *
     * @return the atmospheric pressure on the sea level in hPa
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getSeaLevelPressure() {
        return seaLevelPressure;
    }

    /**
     * Returns the atmospheric pressure on the ground level in hPa.
     *
     * @return the atmospheric pressure on the ground level in hPa
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getGroundLevelPressure() {
        return groundLevelPressure;
    }

    /**
     * Returns the temp_kf value.
     *
     * @return the temp_kf value
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getTempKf() {
        return tempKf;
    }
}
