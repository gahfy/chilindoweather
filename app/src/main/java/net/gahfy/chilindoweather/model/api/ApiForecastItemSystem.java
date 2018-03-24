package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.ApiConstants.JSON_FORECAST_ITEM_SYSTEM_POD;

/**
 * The forecast system as it is returned by OpenWeatherMap API
 *
 * @version 2.5
 */
public class ApiForecastItemSystem {
    /**
     * API pod value
     */
    @Json(name = JSON_FORECAST_ITEM_SYSTEM_POD)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String pod;

    /**
     * Returns the API pod value.
     *
     * @return the API pod value
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getPod() {
        return pod;
    }
}
