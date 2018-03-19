package net.gahfy.chilindoweather.ui.forecast;

import net.gahfy.chilindoweather.model.weather.DayWeatherForecast;
import net.gahfy.chilindoweather.ui.common.CommonView;

import java.util.List;

public interface ForecastView extends CommonView {
    void setDayWeatherForecastList(List<DayWeatherForecast> dayWeatherForecastList, int temperatureIndex, int speedIndex);
}
