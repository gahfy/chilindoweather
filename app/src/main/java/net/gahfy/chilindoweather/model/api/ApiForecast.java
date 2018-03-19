package net.gahfy.chilindoweather.model.api;


import android.support.annotation.Nullable;

import com.squareup.moshi.Json;

import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_CITY;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ERROR_MESSAGE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_FORECAST_LIST;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_HTTP_CODE;
import static net.gahfy.chilindoweather.utils.constants.ApiConstants.JSON_FORECAST_ITEM_COUNT;

public class ApiForecast {
    @Json(name = JSON_FORECAST_ITEM_COUNT)
    @Nullable
    private Integer itemCount;

    @Json(name = JSON_FORECAST_HTTP_CODE)
    @Nullable
    private Integer httpCode;

    @Json(name = JSON_FORECAST_ERROR_MESSAGE)
    @Nullable
    private String errorMessage;

    @Json(name = JSON_FORECAST_CITY)
    @Nullable
    private ApiCity city;

    @Json(name = JSON_FORECAST_FORECAST_LIST)
    @Nullable
    private ApiForecastItem[] forecastItemList;

    @Nullable
    public Integer getItemCount() {
        return itemCount;
    }

    @Nullable
    public Integer getHttpCode() {
        return httpCode;
    }

    @Nullable
    public String getErrorMessage() {
        return errorMessage;
    }

    @Nullable
    public ApiCity getCity() {
        return city;
    }

    @Nullable
    public ApiForecastItem[] getForecastItemList() {
        return forecastItemList;
    }
}
