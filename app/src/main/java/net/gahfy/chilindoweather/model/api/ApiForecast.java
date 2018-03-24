package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.ApiConstants.JSON_FORECAST_CITY;
import static net.gahfy.chilindoweather.utils.ApiConstants.JSON_FORECAST_ERROR_MESSAGE;
import static net.gahfy.chilindoweather.utils.ApiConstants.JSON_FORECAST_FORECAST_LIST;
import static net.gahfy.chilindoweather.utils.ApiConstants.JSON_FORECAST_HTTP_CODE;
import static net.gahfy.chilindoweather.utils.ApiConstants.JSON_FORECAST_ITEM_COUNT;

/**
 * List of weather forecasts as it is returned by OpenWeatherMap API
 *
 * @version 2.5
 */
public final class ApiForecast {
    /**
     * Number of weather forecast in the response
     */
    @Json(name = JSON_FORECAST_ITEM_COUNT)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer itemCount;

    /**
     * HTTP code of the response
     */
    @Json(name = JSON_FORECAST_HTTP_CODE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer httpCode;

    /**
     * Error message
     */
    @Json(name = JSON_FORECAST_ERROR_MESSAGE)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private String errorMessage;

    /**
     * City to which forecasts apply
     */
    @Json(name = JSON_FORECAST_CITY)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiCity city;

    /**
     * List of weather forecast
     */
    @Json(name = JSON_FORECAST_FORECAST_LIST)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiForecastItem[] forecastItemList;

    /**
     * Returns the number of weather forecasts in the response.
     * @return the number of weather forecasts in the response
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getItemCount() {
        return itemCount;
    }

    /**
     * Returns the HTTP code of the response.
     * @return the HTTP code of the response
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getHttpCode() {
        return httpCode;
    }

    /**
     * Returns the error message.
     * @return the error message
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Returns the city to which the forecasts apply.
     * @return the city to which the forecasts apply
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiCity getCity() {
        return city;
    }

    /**
     * Returns the list of weather forecasts.
     * @return the list of weather forecasts
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiForecastItem[] getForecastItemList() {
        return forecastItemList;
    }
}
