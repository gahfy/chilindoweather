package net.gahfy.chilindoweather.utils.weather;

import android.support.annotation.Nullable;

import net.gahfy.chilindoweather.model.api.ApiMeasurements;

import static net.gahfy.chilindoweather.utils.UnitUtils.CELSIUS_INDEX;
import static net.gahfy.chilindoweather.utils.UnitUtils.FAHRENHEIT_INDEX;

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
    // Safe as we want to keep the information that the exception may be thrown
    @SuppressWarnings("squid:RedundantThrowsDeclarationCheck")
    public static Integer getTemperature(@Nullable final Double temperature, final int preferredTemperatureIndex) throws IllegalArgumentException {
        if (preferredTemperatureIndex != CELSIUS_INDEX && preferredTemperatureIndex != FAHRENHEIT_INDEX) {
            throw new IllegalArgumentException("preferredTemperatureIndex must be one of CELSIUS_INDEX or FAHRENHEIT_INDEX.");
        }
        if (temperature != null) {
            if (preferredTemperatureIndex == CELSIUS_INDEX) {
                return (int) (temperature - KELVIN_TO_CELSIUS_DIFFERENCE);
            } else {
                return (int) (temperature * KELVIN_TO_FAHRENHEIT_MULTIPLIER - KELVIN_TO_FAHRENHEIT_DIFFERENCE);
            }
        }
        return null;
    }
}
