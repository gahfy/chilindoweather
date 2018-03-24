package net.gahfy.chilindoweather.utils.weather;

import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiWind;

import static net.gahfy.chilindoweather.utils.UnitUtils.METERS_INDEX;
import static net.gahfy.chilindoweather.utils.UnitUtils.MILES_INDEX;

public final class WindUtils {
    public static final int WIND_TO_FROM_DIFFERENCE = 180;
    private static final int DIRECTION_NORMALIZED_MAX_ANGLE = 90;
    private static final double METERS_SECOND_TO_MPH_MULTIPLIER = 2.23694;

    private WindUtils() {
    }

    @Nullable
    public static Double getWindSpeed(@Nullable final ApiWind apiWind) {
        return apiWind != null ? apiWind.getSpeed() : null;
    }

    @Nullable
    public static Integer getWindDirection(@Nullable final ApiWind apiWind) {
        return apiWind != null ?
                apiWind.getDirection() != null ?
                        apiWind.getDirection().intValue() :
                        null :
                null;
    }

    public static int getWindDirectionNormalized(@Nullable final Integer windDirection) {
        if (windDirection != null) {
            return windDirection % DIRECTION_NORMALIZED_MAX_ANGLE;
        }
        return 0;
    }

    @StringRes
    public static int getWindDirectionOrientation(@Nullable final Integer windDirection) {
        if (windDirection != null) {
            switch ((windDirection / DIRECTION_NORMALIZED_MAX_ANGLE) % 4) {
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
        return R.string.empty;
    }

    @Nullable
    public static Integer getWindSpeed(@Nullable final Double windSpeed, final int preferredSpeedIndex) throws IllegalArgumentException {
        if (preferredSpeedIndex != METERS_INDEX && preferredSpeedIndex != MILES_INDEX) {
            throw new IllegalArgumentException("preferredSpeedIndex must be one of METERS_INDEX or MILES_INDEX.");
        }
        if (windSpeed != null) {
            if (preferredSpeedIndex == METERS_INDEX) {
                return windSpeed.intValue();
            } else {
                return (int) (windSpeed * METERS_SECOND_TO_MPH_MULTIPLIER);
            }
        }
        return null;
    }
}
