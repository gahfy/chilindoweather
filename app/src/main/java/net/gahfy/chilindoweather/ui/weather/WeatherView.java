package net.gahfy.chilindoweather.ui.weather;

import net.gahfy.chilindoweather.model.weather.CurrentWeather;
import net.gahfy.chilindoweather.ui.common.CommonView;

public interface WeatherView extends CommonView {
    void showCurrentWeather(CurrentWeather currentWeather, int preferredTemperatureIndex, int preferredSpeedIndex);

    void showTitle(String title);
}
