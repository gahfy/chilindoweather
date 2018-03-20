package net.gahfy.chilindoweather.model.weather;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiWeather;
import net.gahfy.chilindoweather.utils.WeatherUtils;
import net.gahfy.chilindoweather.utils.unit.UnitUtils;

public class CurrentWeather {
    @Nullable
    private Integer calculationTimestamp;

    @DrawableRes
    private int iconResId;

    @Nullable
    private Double temperature;

    private Integer windDirection;

    private Double windSpeed;

    private Integer humidity;

    private Double atmosphericPressure;

    private String city;

    @StringRes
    private int conditionDescription;

    public CurrentWeather(ApiWeather apiWeather) {
        calculationTimestamp = apiWeather.getCalculationTimestamp();
        city = apiWeather.getCityName();

        if (apiWeather.getMeasurements() != null) {
            temperature = apiWeather.getMeasurements().getTemperature();
            humidity = apiWeather.getMeasurements().getHumidity();
            if (apiWeather.getMeasurements().getPressure() != null) {
                atmosphericPressure = apiWeather.getMeasurements().getPressure();
            } else if (apiWeather.getMeasurements().getGroundLevelPressure() != null) {
                atmosphericPressure = apiWeather.getMeasurements().getGroundLevelPressure();
            } else if (apiWeather.getMeasurements().getSeaLevelPressure() != null) {
                atmosphericPressure = apiWeather.getMeasurements().getSeaLevelPressure();
            } else {
                atmosphericPressure = null;
            }
        } else {
            temperature = null;
            humidity = null;
            atmosphericPressure = null;
        }

        if (apiWeather.getWind() != null) {
            windDirection = apiWeather.getWind().getDirection() != null ? apiWeather.getWind().getDirection().intValue() : null;
            windSpeed = apiWeather.getWind().getSpeed();
        } else {
            windDirection = null;
            windSpeed = null;
        }

        if (apiWeather.getCondition() != null && apiWeather.getCondition().length >= 1) {
            iconResId = WeatherUtils.getIconResId(apiWeather.getCondition()[0].getIconId());
            conditionDescription = WeatherUtils.getConditionDescriptionResId(apiWeather.getCondition()[0].getId());
        } else {
            iconResId = 0;
            conditionDescription = 0;
        }
    }

    @Nullable
    public Integer getCalculationTimestamp() {
        return calculationTimestamp;
    }

    @DrawableRes
    public int getIconResId() {
        return iconResId;
    }

    @StringRes
    public int getConditionDescriptionResId() {
        return conditionDescription;
    }

    @Nullable
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

    public Integer getHumidity() {
        return humidity;
    }

    public Integer getAtmosphericPressure() {
        return atmosphericPressure != null ? atmosphericPressure.intValue() : null;
    }

    public String getCity() {
        return city;
    }
}
