package net.gahfy.chilindoweather.model.weather;

import android.content.Context;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiForecastItem;
import net.gahfy.chilindoweather.utils.ContextUtils;
import net.gahfy.chilindoweather.utils.StringUtils;
import net.gahfy.chilindoweather.utils.weather.ConditionUtils;
import net.gahfy.chilindoweather.utils.weather.MeasurementsUtils;
import net.gahfy.chilindoweather.utils.weather.WindUtils;

public final class InstantWeatherForecast extends AbstractWeather {
    /**
     * The creator used by Parcelable implementations.
     * Code generated by Android Studio.
     */
    static final Creator<InstantWeatherForecast> CREATOR = new Creator<InstantWeatherForecast>() {
        @Override
        public InstantWeatherForecast createFromParcel(Parcel in) {
            return new InstantWeatherForecast(in);
        }

        @Override
        public InstantWeatherForecast[] newArray(int size) {
            return new InstantWeatherForecast[size];
        }
    };

    InstantWeatherForecast(@NonNull final ApiForecastItem apiForecastItem) {
        super(
                apiForecastItem.getCalculationTimestamp(),
                ConditionUtils.getIconResId(apiForecastItem.getCondition()),
                ConditionUtils.getDescriptionResId(apiForecastItem.getCondition()),
                MeasurementsUtils.getTemperature(apiForecastItem.getMeasurements()),
                WindUtils.getWindDirection(apiForecastItem.getWind()),
                WindUtils.getWindSpeed(apiForecastItem.getWind())
        );
    }

    private InstantWeatherForecast(Parcel in) {
        super(in);
    }

    /**
     * Returns the time to which the forecast applies.
     *
     * @param context Context in which the application is running
     * @return the time to which the forecast applies
     */
    @NonNull
    public String getCalculationTime(@NonNull final Context context) {
        if (calculationTimestamp != null) {
            return StringUtils.formatDate(
                    ContextUtils.getLocale(context),
                    context.getString(R.string.forecast_time_format),
                    calculationTimestamp
            );
        }
        return context.getString(R.string.empty);
    }

    /**
     * Returns the timestamp to which the forecast applies.
     * @return the timestamp to which the forecast applies
     */
    @Nullable
    final Integer getCalculationTimestamp() {
        return calculationTimestamp;
    }
}
