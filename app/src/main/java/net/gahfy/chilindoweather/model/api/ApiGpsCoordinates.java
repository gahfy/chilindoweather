package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_GPS_COORDINATES_LATITUDE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_GPS_COORDINATES_LONGITUDE;

/**
 * Gps coordinates as they are returned by OpenWeatherMap API
 */
public class ApiGpsCoordinates {
    /**
     * Latitude of the GPS coordinates
     */
    @Json(name = JSON_GPS_COORDINATES_LATITUDE)
    @Nullable
    private Double latitude;

    /**
     * Longitude of the GPS coordinates
     */
    @Json(name = JSON_GPS_COORDINATES_LONGITUDE)
    @Nullable
    private Double longitude;

    /**
     * Returns the latitude of the GPS coordinates.
     *
     * @return the latitude of the GPS coordinates
     */
    @Nullable
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of the GPS coordinates.
     *
     * @return the longitude of the GPS coordinates
     */
    @Nullable
    public Double getLongitude() {
        return longitude;
    }
}
