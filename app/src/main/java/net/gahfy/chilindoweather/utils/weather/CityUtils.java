package net.gahfy.chilindoweather.utils.weather;


import android.support.annotation.Nullable;

import net.gahfy.chilindoweather.model.api.ApiCity;

public final class CityUtils {
    private CityUtils() {
    }

    @Nullable
    public static String getCityName(@Nullable final ApiCity apiCity) {
        return apiCity != null ? apiCity.getName() : null;
    }
}
