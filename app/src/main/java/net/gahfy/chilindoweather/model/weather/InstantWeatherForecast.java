package net.gahfy.chilindoweather.model.weather;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.utils.StringUtils;
import net.gahfy.chilindoweather.utils.unit.UnitUtils;

public class InstantWeatherForecast implements Parcelable {
    public static final Creator<InstantWeatherForecast> CREATOR = new Creator<InstantWeatherForecast>() {
        @Override
        public InstantWeatherForecast createFromParcel(Parcel in) {
            return new InstantWeatherForecast(in);
        }

        @Override
        public InstantWeatherForecast[] newArray(int size) {
            return new InstantWeatherForecast[size];
        }
    };
    private Integer calculationTimestamp;
    @DrawableRes
    private int conditionIcon;
    @DrawableRes
    private int conditionDescription;
    private Double temperature;
    private Integer windDirection;
    private Double windSpeed;

    public InstantWeatherForecast(Integer calculationTimestamp, int conditionIcon, int conditionDescription, Double temperature, Integer windDirection, Double windSpeed) {
        this.calculationTimestamp = calculationTimestamp;
        this.conditionIcon = conditionIcon;
        this.conditionDescription = conditionDescription;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.temperature = temperature;
    }

    protected InstantWeatherForecast(Parcel in) {
        if (in.readByte() == 0) {
            calculationTimestamp = null;
        } else {
            calculationTimestamp = in.readInt();
        }
        conditionIcon = in.readInt();
        conditionDescription = in.readInt();
        if (in.readByte() == 0) {
            temperature = null;
        } else {
            temperature = in.readDouble();
        }
        if (in.readByte() == 0) {
            windDirection = null;
        } else {
            windDirection = in.readInt();
        }
        if (in.readByte() == 0) {
            windSpeed = null;
        } else {
            windSpeed = in.readDouble();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (calculationTimestamp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(calculationTimestamp);
        }
        dest.writeInt(conditionIcon);
        dest.writeInt(conditionDescription);
        if (temperature == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(temperature);
        }
        if (windDirection == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(windDirection);
        }
        if (windSpeed == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(windSpeed);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Integer getCalculationTimestamp() {
        return calculationTimestamp;
    }

    public int getConditionIcon() {
        return conditionIcon;
    }

    public int getConditionDescription() {
        return conditionDescription;
    }


    public Integer getTemperature(int preferredTemperatureIndex) {
        if (temperature == null) {
            return null;
        } else if (preferredTemperatureIndex == UnitUtils.CELSIUS_INDEX) {
            return (int) (temperature - 273.15);
        } else if (preferredTemperatureIndex == UnitUtils.FAHRENHEIT_INDEX) {
            return (int) (temperature * (9.0 / 5.0) - 459.67);
        } else {
            return temperature.intValue();
        }
    }

    @Nullable
    public Integer getWindDirection() {
        return windDirection;
    }

    @Nullable
    public Integer getWindDirectionNormalized() {
        return windDirection != null ? windDirection % 90 : null;
    }

    @StringRes
    public int getWindDirectionOrientation() {
        if (windDirection != null) {
            switch ((windDirection / 90) % 4) {
                case 0:
                    return R.string.north_abbr;
                case 1:
                    return R.string.east_abbr;
                case 2:
                    return R.string.south_abbr;
                case 3:
                    return R.string.west_abbr;
            }
        }
        return 0;
    }

    @Nullable
    public Integer getWindSpeed(int preferredSpeedIndex) {
        if (windSpeed == null) {
            return null;
        } else if (preferredSpeedIndex == UnitUtils.METERS_INDEX) {
            return windSpeed.intValue();
        } else if (preferredSpeedIndex == UnitUtils.MILES_INDEX) {
            return (int) (windSpeed * 2.23694);
        } else {
            return windSpeed.intValue();
        }
    }


    @NonNull
    public String getCalculationTime(@NonNull final Context context) {
        if (calculationTimestamp != null) {
            return StringUtils.formatDate(
                    context.getResources().getConfiguration().locale,
                    context.getString(R.string.forecast_time_format),
                    calculationTimestamp
            );
        }
        return context.getString(R.string.empty);
    }
}
