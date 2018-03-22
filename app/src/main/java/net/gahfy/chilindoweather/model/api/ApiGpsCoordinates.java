package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_GPS_COORDINATES_LATITUDE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_GPS_COORDINATES_LONGITUDE;

/**
 * Gps coordinates as they are returned by OpenWeatherMap API
 * @version 2.5
 */
public final class ApiGpsCoordinates {
    /**
     * Latitude of the GPS coordinates
     */
    @Json(name = JSON_GPS_COORDINATES_LATITUDE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double latitude;

    /**
     * Longitude of the GPS coordinates
     */
    @Json(name = JSON_GPS_COORDINATES_LONGITUDE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Double longitude;

    /**
     * Returns the latitude of the GPS coordinates.
     *
     * @return the latitude of the GPS coordinates
     */
    @Nullable
    // Safe as we want to provide all getters for POJOs
    @SuppressWarnings("unused")
    public final Double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of the GPS coordinates.
     *
     * @return the longitude of the GPS coordinates
     */
    @Nullable
    // Safe as we want to provide all getters for POJOs
    @SuppressWarnings("unused")
    public final Double getLongitude() {
        return longitude;
    }
}
