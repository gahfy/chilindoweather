package net.gahfy.chilindoweather.model.weather;

import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiWeather;

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
            iconResId = getIconResId(apiWeather.getCondition()[0].getIconId());
            conditionDescription = getConditionDescriptionResId(apiWeather.getCondition()[0].getId());
        } else {
            iconResId = 0;
            conditionDescription = 0;
        }
    }

    @DrawableRes
    private static int getIconResId(@Nullable String icon) {
        if (icon != null) {
            switch (icon) {
                case "01d":
                    return R.drawable.clear_sky_day;
                case "01n":
                    return R.drawable.clear_sky_night;
                case "02d":
                    return R.drawable.few_clouds_day;
                case "02n":
                    return R.drawable.few_clouds_night;
                case "03d":
                case "03n":
                    return R.drawable.scattered_clouds;
                case "04d":
                case "04n":
                    return R.drawable.broken_clouds;
                case "09d":
                case "09n":
                    return R.drawable.shower_rain;
                case "10d":
                    return R.drawable.rain_day;
                case "10n":
                    return R.drawable.rain_night;
                case "11d":
                case "11n":
                    return R.drawable.thunderstorm;
                case "13d":
                case "13n":
                    return R.drawable.snow;
                case "50d":
                case "50n":
                    return R.drawable.mist;

            }
        }
        return 0;
    }

    @StringRes
    public static int getConditionDescriptionResId(Integer conditionId) {
        if (conditionId != null) {
            switch (conditionId) {
                /*
                 In order to generate this block, copy paste block of conditions from strings.xml.
                 Then replace all, with regex checked:

                 Search pattern:
                 <string name="condition_([\d]{3})_description">[^<]+</string>

                 Replace by:
                 case $1: return R.string.condition_$1_description;
                 */
                case 200:
                    return R.string.condition_200_description;
                case 201:
                    return R.string.condition_201_description;
                case 202:
                    return R.string.condition_202_description;
                case 210:
                    return R.string.condition_210_description;
                case 211:
                    return R.string.condition_211_description;
                case 212:
                    return R.string.condition_212_description;
                case 221:
                    return R.string.condition_221_description;
                case 230:
                    return R.string.condition_230_description;
                case 231:
                    return R.string.condition_231_description;
                case 232:
                    return R.string.condition_232_description;

                case 300:
                    return R.string.condition_300_description;
                case 301:
                    return R.string.condition_301_description;
                case 302:
                    return R.string.condition_302_description;
                case 310:
                    return R.string.condition_310_description;
                case 311:
                    return R.string.condition_311_description;
                case 312:
                    return R.string.condition_312_description;
                case 313:
                    return R.string.condition_313_description;
                case 314:
                    return R.string.condition_314_description;
                case 321:
                    return R.string.condition_321_description;

                case 500:
                    return R.string.condition_500_description;
                case 501:
                    return R.string.condition_501_description;
                case 502:
                    return R.string.condition_502_description;
                case 503:
                    return R.string.condition_503_description;
                case 504:
                    return R.string.condition_504_description;
                case 511:
                    return R.string.condition_511_description;
                case 520:
                    return R.string.condition_520_description;
                case 521:
                    return R.string.condition_521_description;
                case 522:
                    return R.string.condition_522_description;
                case 531:
                    return R.string.condition_531_description;

                case 600:
                    return R.string.condition_600_description;
                case 601:
                    return R.string.condition_601_description;
                case 602:
                    return R.string.condition_602_description;
                case 611:
                    return R.string.condition_611_description;
                case 612:
                    return R.string.condition_612_description;
                case 615:
                    return R.string.condition_615_description;
                case 616:
                    return R.string.condition_616_description;
                case 620:
                    return R.string.condition_620_description;
                case 621:
                    return R.string.condition_621_description;
                case 622:
                    return R.string.condition_622_description;

                case 701:
                    return R.string.condition_701_description;
                case 711:
                    return R.string.condition_711_description;
                case 721:
                    return R.string.condition_721_description;
                case 731:
                    return R.string.condition_731_description;
                case 741:
                    return R.string.condition_741_description;
                case 751:
                    return R.string.condition_751_description;
                case 761:
                    return R.string.condition_761_description;
                case 762:
                    return R.string.condition_762_description;
                case 771:
                    return R.string.condition_771_description;
                case 781:
                    return R.string.condition_781_description;

                case 800:
                    return R.string.condition_800_description;
                case 801:
                    return R.string.condition_801_description;
                case 802:
                    return R.string.condition_802_description;
                case 803:
                    return R.string.condition_803_description;
                case 804:
                    return R.string.condition_804_description;

                case 900:
                    return R.string.condition_900_description;
                case 901:
                    return R.string.condition_901_description;
                case 902:
                    return R.string.condition_902_description;
                case 903:
                    return R.string.condition_903_description;
                case 904:
                    return R.string.condition_904_description;
                case 905:
                    return R.string.condition_905_description;
                case 906:
                    return R.string.condition_906_description;

                case 951:
                    return R.string.condition_951_description;
                case 952:
                    return R.string.condition_952_description;
                case 953:
                    return R.string.condition_953_description;
                case 954:
                    return R.string.condition_954_description;
                case 955:
                    return R.string.condition_955_description;
                case 956:
                    return R.string.condition_956_description;
                case 957:
                    return R.string.condition_957_description;
                case 958:
                    return R.string.condition_958_description;
                case 959:
                    return R.string.condition_959_description;
                case 960:
                    return R.string.condition_960_description;
                case 961:
                    return R.string.condition_961_description;
                case 962:
                    return R.string.condition_962_description;
            }
        }
        return 0;
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
    public Integer getTemperature() {
        return temperature != null ? temperature.intValue() : null;
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
            switch (windDirection / 90) {
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
    public Integer getWindSpeed() {
        return windSpeed != null ? windSpeed.intValue() : null;
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
