package net.gahfy.chilindoweather.model.api;


import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_CLOUDS_CLOUDINESS;

/**
 * Clouds as it is returned by OpenWeatherMap API
 * @version 2.5
 */
public final class ApiClouds {
    /**
     * Cloudiness in percent
     */
    @Json(name = JSON_CLOUDS_CLOUDINESS)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer cloudiness;

    /**
     * Returns the cloudiness in percent.
     *
     * @return the cloudiness in percent
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getCloudiness() {
        return cloudiness;
    }
}
