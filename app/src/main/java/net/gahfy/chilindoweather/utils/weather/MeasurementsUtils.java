package net.gahfy.chilindoweather.utils.weather;

import android.support.annotation.Nullable;

import net.gahfy.chilindoweather.model.api.ApiMeasurements;
import net.gahfy.chilindoweather.utils.unit.UnitUtils;

public final class MeasurementsUtils {
    private static final double KELVIN_TO_CELSIUS_DIFFERENCE = 273.15;
    private static final double KELVIN_TO_FAHRENHEIT_MULTIPLIER = 1.8;
    private static final double KELVIN_TO_FAHRENHEIT_DIFFERENCE = 459.67;

    private MeasurementsUtils() {
    }

    @Nullable
    public static Double getTemperature(@Nullable final ApiMeasurements apiMeasurements) {
        return apiMeasurements != null ? apiMeasurements.getTemperature() : null;
    }

    @Nullable
    public static Integer getHumidity(@Nullable final ApiMeasurements apiMeasurements) {
        return apiMeasurements != null ? apiMeasurements.getHumidity() : null;
    }

    @Nullable
    public static Double getAtmosphericPressure(@Nullable final ApiMeasurements apiMeasurements) {
        if (apiMeasurements != null) {
            if (apiMeasurements.getPressure() != null) {
                return apiMeasurements.getPressure();
            } else if (apiMeasurements.getGroundLevelPressure() != null) {
                return apiMeasurements.getGroundLevelPressure();
            } else if (apiMeasurements.getSeaLevelPressure() != null) {
                return apiMeasurements.getSeaLevelPressure();
            }
        }
        return null;
    }


    @Nullable
    public static Integer getTemperature(@Nullable final Double temperature, final int preferredTemperatureIndex) {
        if (temperature != null) {
            if (preferredTemperatureIndex == UnitUtils.CELSIUS_INDEX) {
                return (int) (temperature - KELVIN_TO_CELSIUS_DIFFERENCE);
            } else if (preferredTemperatureIndex == UnitUtils.FAHRENHEIT_INDEX) {
                return (int) (temperature * KELVIN_TO_FAHRENHEIT_MULTIPLIER - KELVIN_TO_FAHRENHEIT_DIFFERENCE);
            }
        }
        return null;
    }
}