package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_SYSTEM_COUNTRY_CODE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_SYSTEM_ID;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_SYSTEM_MESSAGE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_SYSTEM_SUNRISE_TIMESTAMP;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_SYSTEM_SUNSET_TIMESTAMP;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_SYSTEM_TYPE;

/**
 * System parameters as it is returned by the OpenWeatherMap API
 */
public class ApiSystem {
    /**
     * API type value
     */
    @Json(name = JSON_SYSTEM_TYPE)
    @Nullable
    private Integer type;

    /**
     * API id value
     */
    @Json(name = JSON_SYSTEM_ID)
    @Nullable
    private Integer id;

    /**
     * API message value
     */
    @Json(name = JSON_SYSTEM_MESSAGE)
    @Nullable
    private Double message;

    /**
     * Country code of the location of the weather
     */
    @Json(name = JSON_SYSTEM_COUNTRY_CODE)
    @Nullable
    private String countryCode;

    /**
     * Timestamp of sunrise at the location of the weather
     */
    @Json(name = JSON_SYSTEM_SUNRISE_TIMESTAMP)
    @Nullable
    private Integer sunriseTimestamp;

    /**
     * Timestamp of sunset at the location of the weather
     */
    @Json(name = JSON_SYSTEM_SUNSET_TIMESTAMP)
    @Nullable
    private Integer sunsetTimestamp;

    /**
     * Returns the API type value.
     *
     * @return the API type value
     */
    @Nullable
    public Integer getType() {
        return type;
    }

    /**
     * Returns the API id value.
     *
     * @return the API id value
     */
    @Nullable
    public Integer getId() {
        return id;
    }

    /**
     * Returns the API message value.
     *
     * @return the API message value
     */
    @Nullable
    public Double getMessage() {
        return message;
    }

    /**
     * Returns the country code of the location of the weather.
     *
     * @return the country code of the location of the weather
     */
    @Nullable
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Returns the timestamp of sunrise at the location of the weather.
     *
     * @return the timestamp of sunrise at the location of the weather
     */
    @Nullable
    public Integer getSunriseTimestamp() {
        return sunriseTimestamp;
    }

    /**
     * Returns the timestamp of sunset at the location of the weather.
     *
     * @return the timestamp of sunset at the location of the weather
     */
    @Nullable
    public Integer getSunsetTimestamp() {
        return sunsetTimestamp;
    }
}
