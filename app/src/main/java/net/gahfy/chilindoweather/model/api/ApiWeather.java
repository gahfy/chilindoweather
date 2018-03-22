package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_BASE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_CALCULATION_TIMESTAMP;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_CITY_ID;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_CITY_NAME;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_CLOUDS;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_CONDITION;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_ERROR_MESSAGE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_GPS_COORDINATES;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_HTTP_CODE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_MEASUREMENTS;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_RAIN;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_SNOW;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_SYSTEM;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_WEATHER_WIND;

/**
 * Weather as it is returned by the OpenWeatherMap API
 * @version 2.5
 */
// Safe as we want API POJOs to be specific (in case API changes) instead of using inheritance
@SuppressWarnings("findbugs:common-java:DuplicatedBlocks")
public final class ApiWeather {
    /**
     * GPS coordinates to which weather forecast applies
     */
    @Json(name = JSON_WEATHER_GPS_COORDINATES)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiGpsCoordinates gpsCoordinates;

    /**
     * Weather condition
     */
    @Json(name = JSON_WEATHER_CONDITION)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiCondition[] condition;

    /**
     * API base value
     */
    @Json(name = JSON_WEATHER_BASE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String base;

    /**
     * Weather measurements
     */
    @Json(name = JSON_WEATHER_MEASUREMENTS)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiMeasurements measurements;

    /**
     * Wind data
     */
    @Json(name = JSON_WEATHER_WIND)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiWind wind;

    /**
     * Cloudiness data
     */
    @Json(name = JSON_WEATHER_CLOUDS)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiClouds clouds;

    /**
     * Rain data
     */
    @Json(name = JSON_WEATHER_RAIN)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiRain rain;

    /**
     * Snow data
     */
    @Json(name = JSON_WEATHER_SNOW)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiSnow snow;

    /**
     * The timestamp when weather forecast has been calculated
     */
    @Json(name = JSON_WEATHER_CALCULATION_TIMESTAMP)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer calculationTimestamp;

    /**
     * System data
     */
    @Json(name = JSON_WEATHER_SYSTEM)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiSystem system;

    /**
     * The unique identifier of the city
     */
    @Json(name = JSON_WEATHER_CITY_ID)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer cityId;

    /**
     * The name of the city
     */
    @Json(name = JSON_WEATHER_CITY_NAME)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String cityName;

    /**
     * The HTTP code of the response
     */
    @Json(name = JSON_WEATHER_HTTP_CODE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer httpCode;

    /**
     * The error message
     */
    @Json(name = JSON_WEATHER_ERROR_MESSAGE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String errorMessage;

    /**
     * Returns the GPS coordinates to which weather forecast applies.
     *
     * @return the GPS coordinates to which weather forecast applies
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiGpsCoordinates getGpsCoordinates() {
        return gpsCoordinates;
    }

    /**
     * Returns the weather condition.
     *
     * @return the weather condition
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiCondition[] getCondition() {
        return condition;
    }

    /**
     * Returns the API base value.
     *
     * @return the API base value
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getBase() {
        return base;
    }

    /**
     * Returns the weather measurements.
     *
     * @return the weather measurements
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiMeasurements getMeasurements() {
        return measurements;
    }

    /**
     * Returns the wind data.
     *
     * @return the wind data
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiWind getWind() {
        return wind;
    }

    /**
     * Returns the cloudiness data.
     *
     * @return the cloudiness data
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiClouds getClouds() {
        return clouds;
    }

    /**
     * Returns the rain data.
     *
     * @return the rain data
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiRain getRain() {
        return rain;
    }

    /**
     * Returns the snow data.
     *
     * @return the snow data
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiSnow getSnow() {
        return snow;
    }

    /**
     * Returns the timestamp when weather forecast has been calculated.
     *
     * @return the timestamp when weather forecast has been calculated
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getCalculationTimestamp() {
        return calculationTimestamp;
    }

    /**
     * Returns the system data.
     *
     * @return the system data
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiSystem getSystem() {
        return system;
    }

    /**
     * Returns the unique identifier of the city.
     *
     * @return the unique identifier of the city
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getCityId() {
        return cityId;
    }

    /**
     * Returns the name of the city.
     *
     * @return the name of the city
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getCityName() {
        return cityName;
    }

    /**
     * Returns the HTTP code of the response.
     *
     * @return the HTTP code of the response
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getHttpCode() {
        return httpCode;
    }

    /**
     * Returns the error message.
     *
     * @return the error message
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getErrorMessage() {
        return errorMessage;
    }
}
