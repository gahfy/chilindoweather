package net.gahfy.chilindoweather.model.api;

import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_CALCULATION_DATETIME;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_CALCULATION_TIMESTAMP;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_CLOUDINESS;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_CONDITION;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_MEASUREMENTS;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_RAIN;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_SNOW;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_SYSTEM;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_WIND;

/**
 * Weather forecast as it is returned by OpenWeatherMap API.
 *
 * @version 2.5
 */
public final class ApiForecastItem {
    /**
     * Timestamp for which the weather forecast has been calculated
     */
    @Json(name = JSON_FORECAST_ITEM_CALCULATION_TIMESTAMP)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private Integer calculationTimestamp;

    /**
     * Measurements of the weather forecast
     */
    @Json(name = JSON_FORECAST_ITEM_MEASUREMENTS)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiMeasurements measurements;

    /**
     * Condition of the weather forecast
     */
    @Json(name = JSON_FORECAST_ITEM_CONDITION)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiCondition[] condition;

    /**
     * Cloudiness data of the weather forecast
     */
    @Json(name = JSON_FORECAST_ITEM_CLOUDINESS)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiClouds cloudiness;

    /**
     * Wind data of the weather forecast
     */
    @Json(name = JSON_FORECAST_ITEM_WIND)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiWind wind;

    /**
     * Rain data of the weather forecast
     */
    @Json(name = JSON_FORECAST_ITEM_RAIN)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiRain rain;

    /**
     * Snow data of the weather forecast
     */
    @Json(name = JSON_FORECAST_ITEM_SNOW)
    @Nullable
    // Safe as the variable is assigned by Moshi
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiSnow snow;

    /**
     * Date and time for which the weather forecast has been calculated
     */
    @Json(name = JSON_FORECAST_ITEM_CALCULATION_DATETIME)
    // Safe as the variable is assigned by Moshi
    @Nullable
    @SuppressWarnings({"UnusedDeclaration"})
    private String calculationDatetime;

    /**
     * API sys value
     */
    @Json(name = JSON_FORECAST_ITEM_SYSTEM)
    // Safe as the variable is assigned by Moshi
    @Nullable
    @SuppressWarnings({"UnusedDeclaration"})
    private ApiForecastItemSystem system;

    /**
     * Returns the timestamp for which the forecast has been calculated.
     * @return the timestamp for which the forecast has been calculated
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final Integer getCalculationTimestamp() {
        return calculationTimestamp;
    }

    /**
     * Returns the measurements of the weather forecast.
     * @return the measurements of the weather forecast
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiMeasurements getMeasurements() {
        return measurements;
    }

    /**
     * Returns the condition of the weather forecast.
     * @return the condition of the weather forecast
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiCondition[] getCondition() {
        return condition;
    }

    /**
     * Returns the cloudiness of the weather forecast.
     * @return the cloudiness of the weather forecast
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiClouds getCloudiness() {
        return cloudiness;
    }

    /**
     * Returns the rain data of the weather forecast.
     *
     * @return the rain data of the weather forecast
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiRain getRain() {
        return rain;
    }

    /**
     * Returns the wind data of the weather forecast.
     * @return the wind data of the weather forecast
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiWind getWind() {
        return wind;
    }

    /**
     * Returns the snow data of the weather forecast.
     * @return the snow data of the weather forecast
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiSnow getSnow() {
        return snow;
    }

    /**
     * Returns the date and time for which the weather forecast has been calculated.
     * @return the date and time for which the weather forecast has been calculated
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final String getCalculationDatetime() {
        return calculationDatetime;
    }

    /**
     * Returns the API sys value.
     *
     * @return the API sys value
     */
    @Nullable
    // Safe as we want to provide all getters being public for POJOs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public final ApiForecastItemSystem getSystem() {
        return system;
    }
}
