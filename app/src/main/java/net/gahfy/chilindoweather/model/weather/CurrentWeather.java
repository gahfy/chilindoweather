package net.gahfy.chilindoweather.model.weather;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiMeasurements;
import net.gahfy.chilindoweather.model.api.ApiWeather;
import net.gahfy.chilindoweather.model.api.ApiWind;
import net.gahfy.chilindoweather.utils.ContextUtils;
import net.gahfy.chilindoweather.utils.StringUtils;
import net.gahfy.chilindoweather.utils.log.Logger;
import net.gahfy.chilindoweather.utils.weather.ConditionUtils;
import net.gahfy.chilindoweather.utils.weather.MeasurementsUtils;
import net.gahfy.chilindoweather.utils.weather.WindUtils;

import static net.gahfy.chilindoweather.utils.unit.UnitUtils.METERS_INDEX;
import static net.gahfy.chilindoweather.utils.weather.WindUtils.WIND_TO_FROM_DIFFERENCE;

/**
 * The current weather as it is displayed in the application.
 */
public final class CurrentWeather implements Parcelable {
    /**
     * The creator used by Parcelable implementations.
     * Code generated by Android Studio.
     */
    public static final Creator<CurrentWeather> CREATOR = new Creator<CurrentWeather>() {
        @Override
        public CurrentWeather createFromParcel(@NonNull final Parcel in) {
            return new CurrentWeather(in);
        }

        @Override
        public CurrentWeather[] newArray(final int size) {
            return new CurrentWeather[size];
        }
    };
    /**
     * Tag to be used for logging
     */
    private static final String TAG = "CurrentWeather";
    /**
     * Timestamp when the weather has been calculated
     */
    @Nullable
    private final Integer calculationTimestamp;
    /**
     * Resource identifier of the icon representing the current condition
     */
    @DrawableRes
    private final int iconResId;
    /**
     * Resource identifier of the text description of the current condition
     */
    @StringRes
    private final int conditionDescription;
    /**
     * Temperature
     */
    @Nullable
    private final Double temperature;
    /**
     * Speed of the wind
     */
    @Nullable
    private final Double windSpeed;
    /**
     * Direction of the wind
     */
    @Nullable
    private final Integer windDirection;
    /**
     * Humidity
     */
    @Nullable
    private final Integer humidity;
    /**
     * Atmospheric pressure
     */
    @Nullable
    private final Double atmosphericPressure;
    /**
     * Name of the city to which the current weather applies
     */
    @Nullable
    private final String city;

    /**
     * Instantiates a CurrentWeather from an ApiWeather.
     *
     * @param apiWeather the ApiWeather instance the values must be taken from
     */
    public CurrentWeather(@NonNull final ApiWeather apiWeather) {
        final ApiMeasurements apiMeasurements = apiWeather.getMeasurements();
        final ApiWind apiWind = apiWeather.getWind();

        calculationTimestamp = apiWeather.getCalculationTimestamp();
        city = apiWeather.getCityName();
        temperature = MeasurementsUtils.getTemperature(apiMeasurements);
        humidity = MeasurementsUtils.getHumidity(apiMeasurements);
        atmosphericPressure = MeasurementsUtils.getAtmosphericPressure(apiMeasurements);
        windDirection = WindUtils.getWindDirection(apiWind);
        windSpeed = WindUtils.getWindSpeed(apiWind);
        iconResId = ConditionUtils.getIconResId(apiWeather.getCondition());
        conditionDescription = ConditionUtils.getDescriptionResId(apiWeather.getCondition());
    }

    /**
     * @author Android Studio Generator
     */
    private CurrentWeather(@NonNull final Parcel in) {
        if (in.readByte() == 0) {
            calculationTimestamp = null;
        } else {
            calculationTimestamp = in.readInt();
        }
        iconResId = in.readInt();
        conditionDescription = in.readInt();
        if (in.readByte() == 0) {
            temperature = null;
        } else {
            temperature = in.readDouble();
        }
        if (in.readByte() == 0) {
            windSpeed = null;
        } else {
            windSpeed = in.readDouble();
        }
        if (in.readByte() == 0) {
            windDirection = null;
        } else {
            windDirection = in.readInt();
        }
        if (in.readByte() == 0) {
            humidity = null;
        } else {
            humidity = in.readInt();
        }
        if (in.readByte() == 0) {
            atmosphericPressure = null;
        } else {
            atmosphericPressure = in.readDouble();
        }
        city = in.readString();
    }

    /**
     * @author Android Studio Generator
     */
    @Override
    public final void writeToParcel(@NonNull final Parcel dest, final int flags) {
        if (calculationTimestamp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(calculationTimestamp);
        }
        dest.writeInt(iconResId);
        dest.writeInt(conditionDescription);
        if (temperature == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(temperature);
        }
        if (windSpeed == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(windSpeed);
        }
        if (windDirection == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(windDirection);
        }
        if (humidity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(humidity);
        }
        if (atmosphericPressure == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(atmosphericPressure);
        }
        dest.writeString(city);
    }

    /**
     * @author Android Studio Generator
     */
    @Override
    public final int describeContents() {
        return 0;
    }

    /**
     * Returns the localized date and time when the weather has been calculated.
     *
     * @param context Context in which the application is running
     * @return the localized date and time when the weather has been calculated
     */
    @NonNull
    public final String getCalculationDateTime(@NonNull final Context context) {
        if (calculationTimestamp != null) {
            return StringUtils.formatDate(
                    ContextUtils.getLocale(context),
                    context.getString(R.string.calculation_date_format),
                    calculationTimestamp
            );
        }
        return context.getString(R.string.empty);
    }

    /**
     * Returns the localized speed of the wind.
     *
     * @param context             Context in which the application is running
     * @param preferredSpeedIndex the index of preferred unit for speed
     * @return the localized speed of the wind
     */
    @NonNull
    public final String getWindSpeed(@NonNull final Context context, int preferredSpeedIndex) {
        try {
            final Integer userWindSpeed = WindUtils.getWindSpeed(windSpeed, preferredSpeedIndex);

            if (userWindSpeed != null) {
                return context.getString(
                        preferredSpeedIndex == METERS_INDEX ? R.string.wind_speed_metric : R.string.wind_speed_imperial,
                        userWindSpeed
                );
            }
        } catch (IllegalArgumentException e) {
            Logger.e(TAG, e);
        }
        return context.getString(R.string.unknown_wind_speed);
    }

    /**
     * Returns the resource identifier of the icon representing the current condition.
     * @return the resource identifier of the icon representing the current condition
     */
    @DrawableRes
    public final int getIconResId() {
        return iconResId;
    }

    /**
     * Returns the resource identifier of the text description of the current condition.
     * @return the resource identifier of the text description of the current condition
     */
    @StringRes
    public final int getConditionDescriptionResId() {
        return conditionDescription;
    }

    /**
     * Returns the localized direction of the wind.
     * @param context Context in which the application is running
     * @return the localized direction of the wind
     */
    @NonNull
    public final String getWindDirection(@NonNull final Context context) {
        if (windDirection != null) {
            return context.getString(
                    R.string.wind_direction_value,
                    WindUtils.getWindDirectionNormalized(windDirection),
                    context.getString(WindUtils.getWindDirectionOrientation(windDirection))
            );
        }
        return context.getString(R.string.unknown_wind_direction);
    }

    /**
     * Returns the angle of rotation for the wind arrow.
     *
     * @return the angle of rotation fot the wind arrow.
     */
    public final int getWindDirectionAngle() {
        return windDirection != null ? windDirection + WIND_TO_FROM_DIFFERENCE : 0;
    }

    /**
     * Returns the localized humidity.
     * @param context Context in which the application is running
     * @return the localized humidity
     */
    @NonNull
    public final String getHumidity(@NonNull final Context context) {
        if (humidity != null) {
            return context.getString(R.string.humidity_value, humidity);
        }
        return context.getString(R.string.unknown_humidity);
    }

    /**
     * Returns the localized atmospheric pressure.
     * @param context Context in which the application is running
     * @return the localized atmospheric pressure
     */
    @NonNull
    public final String getAtmosphericPressure(@NonNull final Context context) {
        if (atmosphericPressure != null) {
            return context.getString(R.string.pressure_value, atmosphericPressure.intValue());
        }
        return context.getString(R.string.unknown_pressure);
    }

    /**
     * Returns the localized temperature.
     * @param context Context in which the application is running
     * @param preferredIndex the index of preferred unit for temperature
     * @return the localized temperature
     */
    @NonNull
    public final String getTemperature(@NonNull final Context context, int preferredIndex) {
        try {
            if (temperature != null) {
                return context.getString(
                        R.string.temperature_value,
                        MeasurementsUtils.getTemperature(temperature, preferredIndex)
                );
            }
        } catch (IllegalArgumentException e) {
            Logger.e(TAG, e);
        }
        return context.getString(R.string.unknown_temperature);
    }

    /**
     * Returns the name of the city to which the current weather applies.
     * @return the name of the city to which the current weather applies
     */
    @Nullable
    public final String getCity() {
        return city;
    }
}
