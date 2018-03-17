package net.gahfy.chilindoweather.model.api;


import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CLOUDS_CLOUDINESS;

/**
 * Clouds as it is returned by OpenWeatherMap API
 */
public class ApiClouds {
    /**
     * Cloudiness in percent
     */
    @Json(name = JSON_CLOUDS_CLOUDINESS)
    @Nullable
    private Integer cloudiness;

    /**
     * Returns the cloudiness in percent.
     *
     * @return the cloudiness in percent
     */
    @Nullable
    public Integer getCloudiness() {
        return cloudiness;
    }
}
