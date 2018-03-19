package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CITY_COUNTRY;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CITY_GPS_COORDINATES;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CITY_ID;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CITY_NAME;

public class ApiCity {
    @Json(name = JSON_CITY_ID)
    @Nullable
    private Integer id;

    @Json(name = JSON_CITY_NAME)
    @Nullable
    private String name;

    @Json(name = JSON_CITY_GPS_COORDINATES)
    @Nullable
    private ApiGpsCoordinates gpsCoordinates;

    @Json(name = JSON_CITY_COUNTRY)
    @Nullable
    private String country;

    @Nullable
    public Integer getId() {
        return id;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public ApiGpsCoordinates getGpsCoordinates() {
        return gpsCoordinates;
    }

    @Nullable
    public String getCountry() {
        return country;
    }
}
