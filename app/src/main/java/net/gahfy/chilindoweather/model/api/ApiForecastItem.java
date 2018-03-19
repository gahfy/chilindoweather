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
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_WIND;

public class ApiForecastItem {
    @Nullable
    @Json(name = JSON_FORECAST_ITEM_CALCULATION_TIMESTAMP)
    private Integer calculationTimestamp;

    @Nullable
    @Json(name = JSON_FORECAST_ITEM_MEASUREMENTS)
    private ApiMeasurements measurements;

    @Nullable
    @Json(name = JSON_FORECAST_ITEM_CONDITION)
    private ApiCondition[] condition;

    @Nullable
    @Json(name = JSON_FORECAST_ITEM_CLOUDINESS)
    private ApiClouds cloudiness;

    @Nullable
    @Json(name = JSON_FORECAST_ITEM_WIND)
    private ApiWind wind;

    @Nullable
    @Json(name = JSON_FORECAST_ITEM_RAIN)
    private ApiRain rain;

    @Nullable
    @Json(name = JSON_FORECAST_ITEM_SNOW)
    private ApiSnow snow;

    @Nullable
    @Json(name = JSON_FORECAST_ITEM_CALCULATION_DATETIME)
    private String calculationDatetime;

    @Nullable
    public Integer getCalculationTimestamp() {
        return calculationTimestamp;
    }

    @Nullable
    public ApiMeasurements getMeasurements() {
        return measurements;
    }

    @Nullable
    public ApiCondition getCondition() {
        return condition == null ? null : (condition.length == 0 ? null : condition[0]);
    }

    @Nullable
    public ApiClouds getCloudiness() {
        return cloudiness;
    }

    @Nullable
    public ApiWind getWind() {
        return wind;
    }

    @Nullable
    public ApiRain getRain() {
        return rain;
    }

    @Nullable
    public ApiSnow getSnow() {
        return snow;
    }

    @Nullable
    public String getCalculationDatetime() {
        return calculationDatetime;
    }
}
