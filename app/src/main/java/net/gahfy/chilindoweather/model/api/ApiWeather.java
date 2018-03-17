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
 * Weather forecast as it is returned by the OpenWeatherMap API
 */
public class ApiWeather {
    /**
     * GPS coordinates to which weather forecast applies
     */
    @Json(name = JSON_WEATHER_GPS_COORDINATES)
    @Nullable
    private ApiGpsCoordinates gpsCoordinates;

    /**
     * Weather condition
     */
    @Json(name = JSON_WEATHER_CONDITION)
    @Nullable
    private ApiCondition[] condition;

    /**
     * API base value
     */
    @Json(name = JSON_WEATHER_BASE)
    @Nullable
    private String base;

    /**
     * Weather measurements
     */
    @Json(name = JSON_WEATHER_MEASUREMENTS)
    @Nullable
    private ApiMeasurements measurements;

    /**
     * Wind data
     */
    @Json(name = JSON_WEATHER_WIND)
    @Nullable
    private ApiWind wind;

    /**
     * Cloudiness data
     */
    @Json(name = JSON_WEATHER_CLOUDS)
    @Nullable
    private ApiClouds clouds;

    /**
     * Rain data
     */
    @Json(name = JSON_WEATHER_RAIN)
    @Nullable
    private ApiRain rain;

    /**
     * Snow data
     */
    @Json(name = JSON_WEATHER_SNOW)
    @Nullable
    private ApiSnow snow;

    /**
     * The timestamp when weather forecast has been calculated
     */
    @Json(name = JSON_WEATHER_CALCULATION_TIMESTAMP)
    @Nullable
    private Integer calculationTimestamp;

    /**
     * System data
     */
    @Json(name = JSON_WEATHER_SYSTEM)
    @Nullable
    private ApiSystem system;

    /**
     * The unique identifier of the city
     */
    @Json(name = JSON_WEATHER_CITY_ID)
    @Nullable
    private Integer cityId;

    /**
     * The name of the city
     */
    @Json(name = JSON_WEATHER_CITY_NAME)
    @Nullable
    private String cityName;

    /**
     * The HTTP code of the response
     */
    @Json(name = JSON_WEATHER_HTTP_CODE)
    @Nullable
    private Integer httpCode;

    /**
     * The error message
     */
    @Json(name = JSON_WEATHER_ERROR_MESSAGE)
    @Nullable
    private String errorMessage;

    /**
     * Returns the GPS coordinates to which weather forecast applies.
     *
     * @return the GPS coordinates to which weather forecast applies
     */
    @Nullable
    public ApiGpsCoordinates getGpsCoordinates() {
        return gpsCoordinates;
    }

    /**
     * Returns the weather condition.
     *
     * @return the weather condition
     */
    @Nullable
    public ApiCondition[] getCondition() {
        return condition;
    }

    /**
     * Returns the API base value.
     *
     * @return the API base value
     */
    @Nullable
    public String getBase() {
        return base;
    }

    /**
     * Returns the weather measurements.
     *
     * @return the weather measurements
     */
    @Nullable
    public ApiMeasurements getMeasurements() {
        return measurements;
    }

    /**
     * Returns the wind data.
     *
     * @return the wind data
     */
    @Nullable
    public ApiWind getWind() {
        return wind;
    }

    /**
     * Returns the cloudiness data.
     *
     * @return the cloudiness data
     */
    @Nullable
    public ApiClouds getClouds() {
        return clouds;
    }

    /**
     * Returns the rain data.
     *
     * @return the rain data
     */
    @Nullable
    public ApiRain getRain() {
        return rain;
    }

    /**
     * Returns the snow data.
     *
     * @return the snow data
     */
    @Nullable
    public ApiSnow getSnow() {
        return snow;
    }

    /**
     * Returns the timestamp when weather forecast has been calculated.
     *
     * @return the timestamp when weather forecast has been calculated
     */
    @Nullable
    public Integer getCalculationTimestamp() {
        return calculationTimestamp;
    }

    /**
     * Returns the system data.
     *
     * @return the system data
     */
    @Nullable
    public ApiSystem getSystem() {
        return system;
    }

    /**
     * Returns the unique identifier of the city.
     *
     * @return the unique identifier of the city
     */
    @Nullable
    public Integer getCityId() {
        return cityId;
    }

    /**
     * Returns the name of the city.
     *
     * @return the name of the city
     */
    @Nullable
    public String getCityName() {
        return cityName;
    }

    /**
     * Returns the HTTP code of the response.
     *
     * @return the HTTP code of the response
     */
    @Nullable
    public Integer getHttpCode() {
        return httpCode;
    }

    /**
     * Returns the error message.
     *
     * @return the error message
     */
    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }
}
