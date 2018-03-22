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
 * @version 2.5
 */
// Safe as we want API POJOs to be specific (in case API changes) instead of using inheritance
@SuppressWarnings("common-java:DuplicatedBlocks")
public final class ApiSystem {
    /**
     * API type value
     */
    @Json(name = JSON_SYSTEM_TYPE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer type;

    /**
     * API id value
     */
    @Json(name = JSON_SYSTEM_ID)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer id;

    /**
     * API message value
     */
    @Json(name = JSON_SYSTEM_MESSAGE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double message;

    /**
     * Country code of the location of the weather
     */
    @Json(name = JSON_SYSTEM_COUNTRY_CODE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String countryCode;

    /**
     * Timestamp of sunrise at the location of the weather
     */
    @Json(name = JSON_SYSTEM_SUNRISE_TIMESTAMP)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer sunriseTimestamp;

    /**
     * Timestamp of sunset at the location of the weather
     */
    @Json(name = JSON_SYSTEM_SUNSET_TIMESTAMP)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer sunsetTimestamp;

    /**
     * Returns the API type value.
     *
     * @return the API type value
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public Integer getType() {
        return type;
    }

    /**
     * Returns the API id value.
     *
     * @return the API id value
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getId() {
        return id;
    }

    /**
     * Returns the API message value.
     *
     * @return the API message value
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Double getMessage() {
        return message;
    }

    /**
     * Returns the country code of the location of the weather.
     *
     * @return the country code of the location of the weather
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getCountryCode() {
        return countryCode;
    }

    /**
     * Returns the timestamp of sunrise at the location of the weather.
     *
     * @return the timestamp of sunrise at the location of the weather
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getSunriseTimestamp() {
        return sunriseTimestamp;
    }

    /**
     * Returns the timestamp of sunset at the location of the weather.
     *
     * @return the timestamp of sunset at the location of the weather
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getSunsetTimestamp() {
        return sunsetTimestamp;
    }
}
