package net.gahfy.chilindoweather.model.weather;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.utils.log.Logger;
import net.gahfy.chilindoweather.utils.weather.MeasurementsUtils;
import net.gahfy.chilindoweather.utils.weather.WindUtils;

import static net.gahfy.chilindoweather.utils.unit.UnitUtils.METERS_INDEX;
import static net.gahfy.chilindoweather.utils.weather.WindUtils.WIND_TO_FROM_DIFFERENCE;

public abstract class AbstractWeather implements Parcelable {
    /**
     * Tag to be used for logging
     */
    private static final String TAG = "InstantWeatherForecast";

    /**
     * Timestamp to which the weather applies
     */
    @Nullable
    final Integer calculationTimestamp;

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
     * Temperature (Kelvin)
     */
    @Nullable
    private final Double temperature;

    /**
     * Direction of the wind
     */
    @Nullable
    private final Integer windDirection;

    /**
     * Speed of the wind (m/s)
     */
    @Nullable
    private final Double windSpeed;

    /**
     * Instantiates a new AbstractWeather.
     *
     * @param calculationTimestamp the timestamp to which the weather applies
     * @param iconResId            the icon of the condition
     * @param conditionDescription the description of the condition
     * @param temperature          the temperature (Kelvin)
     * @param windDirection        the direction of the wind
     * @param windSpeed            the speed of the wind (m/s)
     */
    AbstractWeather(
            @Nullable final Integer calculationTimestamp,
            @DrawableRes final int iconResId,
            @StringRes final int conditionDescription,
            @Nullable final Double temperature,
            @Nullable final Integer windDirection,
            @Nullable final Double windSpeed) {
        this.calculationTimestamp = calculationTimestamp;
        this.iconResId = iconResId;
        this.conditionDescription = conditionDescription;
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
    }

    /**
     * Instantiates a new AbstractWeather with Parcel containing the data.
     *
     * @param in Parcel containing the data used to instantiates AbstractWeather
     * @author Android Studio Generator
     */
    AbstractWeather(Parcel in) {
        this(
                // calculationTimestamp
                (in.readByte() == 0) ? null : in.readInt(),
                // conditionIcon
                in.readInt(),
                // conditionDescription
                in.readInt(),
                // temperature
                (in.readByte() == 0) ? null : in.readDouble(),
                // windDirection
                (in.readByte() == 0) ? null : in.readInt(),
                // windSpeed
                (in.readByte() == 0) ? null : in.readDouble()
        );
    }

    /**
     * Writes the data of the class to the specified Parcel.
     *
     * @param dest  the Parcel in which to write the data
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (calculationTimestamp != null ? 1 : 0));
        if (calculationTimestamp != null) {
            dest.writeInt(calculationTimestamp);
        }
        dest.writeInt(iconResId);
        dest.writeInt(conditionDescription);

        dest.writeByte((byte) (temperature != null ? 1 : 0));
        if (temperature != null) {
            dest.writeDouble(temperature);
        }

        dest.writeByte((byte) (windDirection != null ? 1 : 0));
        if (windDirection != null) {
            dest.writeInt(windDirection);
        }

        dest.writeByte((byte) (windSpeed != null ? 1 : 0));
        if (windSpeed != null) {
            dest.writeDouble(windSpeed);
        }
    }

    /**
     * @author Android Studio Generator
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Returns the localized temperature.
     *
     * @param context        Context in which the application is running
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
     * Returns the resource identifier of the icon representing the forecast condition.
     *
     * @return the resource identifier of the icon representing the forecast condition
     */
    @DrawableRes
    public final int getIconResId() {
        return iconResId;
    }

    /**
     * Returns the resource identifier of the text description of the forecast condition.
     *
     * @return the resource identifier of the text description of the forecast condition
     */
    @StringRes
    public final int getConditionDescriptionResId() {
        return conditionDescription;
    }

    /**
     * Returns the localized direction of the wind.
     *
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
}
