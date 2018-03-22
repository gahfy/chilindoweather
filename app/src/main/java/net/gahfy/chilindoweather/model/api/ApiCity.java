package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CITY_COUNTRY;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CITY_GPS_COORDINATES;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CITY_ID;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CITY_NAME;

/**
 * City as it is returned by OpenWeatherMap API
 *
 * @version 2.5
 */
// Safe as we want API POJOs to be specific (in case API changes) instead of using inheritance
@SuppressWarnings("common-java:DuplicatedBlocks")
public final class ApiCity {
    /**
     * Unique identifier of the city
     */
    @Json(name = JSON_CITY_ID)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer id;

    /**
     * Name of the city
     */
    @Json(name = JSON_CITY_NAME)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String name;

    /**
     * Gps coordinates of the city
     */
    @Json(name = JSON_CITY_GPS_COORDINATES)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiGpsCoordinates gpsCoordinates;

    /**
     * Country ISO code of the city
     */
    @Json(name = JSON_CITY_COUNTRY)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String country;

    /**
     * Returns the unique identifier of the city.
     * @return the unique identifier of the city
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getId() {
        return id;
    }

    /**
     * Returns the name of the city.
     * @return the name of the city
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getName() {
        return name;
    }

    /**
     * Returns the GPS coordinates of the city.
     * @return the GPS coordinates of the city
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiGpsCoordinates getGpsCoordinates() {
        return gpsCoordinates;
    }

    /**
     * Returns the country ISO code of the city.
     * @return the country ISO code of the city
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getCountry() {
        return country;
    }
}
