package net.gahfy.chilindoweather.model.weather;

import net.gahfy.chilindoweather.model.api.ApiForecast;
import net.gahfy.chilindoweather.model.api.ApiForecastItem;
import net.gahfy.chilindoweather.utils.DateUtils;
import net.gahfy.chilindoweather.utils.WeatherUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DayWeatherForecast {
    private Integer dayTimestamp;
    private List<InstantWeatherForecast> forecastList;

    public DayWeatherForecast(Integer dayTimestamp, List<InstantWeatherForecast> forecastList) {
        this.dayTimestamp = dayTimestamp;
        this.forecastList = forecastList;
    }

    public static List<DayWeatherForecast> getDayWeatherForecastList(ApiForecast apiForecast) {
        ArrayList<DayWeatherForecast> dayWeatherForecasts = new ArrayList<>();
        if (apiForecast.getForecastItemList() != null) {
            for (ApiForecastItem apiForecastItem : apiForecast.getForecastItemList()) {
                Integer calculationTimestamp = apiForecastItem.getCalculationTimestamp();
                int iconResId = WeatherUtils.getIconResId(apiForecastItem.getCondition() == null ? null : apiForecastItem.getCondition().getIconId());
                int conditionDescription = WeatherUtils.getConditionDescriptionResId(apiForecastItem.getCondition() == null ? null : apiForecastItem.getCondition().getId());
                Integer windDirection = apiForecastItem.getWind() == null ? null : (apiForecastItem.getWind().getDirection() == null ? null : apiForecastItem.getWind().getDirection().intValue());
                Double windSpeed = apiForecastItem.getWind() == null ? null : apiForecastItem.getWind().getSpeed();
                Double temperature = apiForecastItem.getMeasurements() == null ? null : apiForecastItem.getMeasurements().getTemperature();

                InstantWeatherForecast instantWeatherForecast = new InstantWeatherForecast(calculationTimestamp, iconResId, conditionDescription, temperature, windDirection, windSpeed);

                Integer midnightTimestamp = DateUtils.getMidnightTimestamp(apiForecastItem.getCalculationTimestamp());
                boolean found = false;
                for (DayWeatherForecast dayWeatherForecast : dayWeatherForecasts) {
                    if (dayWeatherForecast.getDayTimestamp() == null && midnightTimestamp == null) {
                        dayWeatherForecast.getForecastList().add(instantWeatherForecast);
                        found = true;
                    } else if (midnightTimestamp != null) {
                        if (midnightTimestamp.equals(dayWeatherForecast.getDayTimestamp())) {
                            dayWeatherForecast.getForecastList().add(instantWeatherForecast);
                            found = true;
                        }
                    }
                }
                if (!found) {
                    List<InstantWeatherForecast> forecastList = new ArrayList<>();
                    forecastList.add(instantWeatherForecast);
                    DayWeatherForecast dayWeatherForecast = new DayWeatherForecast(midnightTimestamp, forecastList);
                    dayWeatherForecasts.add(dayWeatherForecast);
                }
            }
        }

        Collections.sort(dayWeatherForecasts, new Comparator<DayWeatherForecast>() {
            @Override
            public int compare(DayWeatherForecast forecast1, DayWeatherForecast forecast2) {
                if (forecast1.getDayTimestamp() == null && forecast2.getDayTimestamp() == null) {
                    return 0;
                } else if (forecast1.getDayTimestamp() == null && forecast2.getDayTimestamp() != null) {
                    return -1;
                } else if (forecast1.getDayTimestamp() != null && forecast2.getDayTimestamp() == null) {
                    return 1;
                } else {
                    return forecast1.getDayTimestamp().compareTo(forecast2.getDayTimestamp());
                }
            }
        });

        for (DayWeatherForecast dayWeatherForecast : dayWeatherForecasts) {
            Collections.sort(dayWeatherForecast.getForecastList(), new Comparator<InstantWeatherForecast>() {
                @Override
                public int compare(InstantWeatherForecast forecast1, InstantWeatherForecast forecast2) {
                    if (forecast1.getCalculationTimestamp() == null && forecast2.getCalculationTimestamp() == null) {
                        return 0;
                    } else if (forecast1.getCalculationTimestamp() == null && forecast2.getCalculationTimestamp() != null) {
                        return -1;
                    } else if (forecast1.getCalculationTimestamp() != null && forecast2.getCalculationTimestamp() == null) {
                        return 1;
                    } else {
                        return forecast1.getCalculationTimestamp().compareTo(forecast2.getCalculationTimestamp());
                    }
                }
            });
        }

        return dayWeatherForecasts;
    }

    public Integer getDayTimestamp() {
        return dayTimestamp;
    }

    public List<InstantWeatherForecast> getForecastList() {
        return forecastList;
    }
}
