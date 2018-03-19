package net.gahfy.chilindoweather.ui.weather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.databinding.ActivityCurrentWeatherBinding;
import net.gahfy.chilindoweather.model.weather.CurrentWeather;
import net.gahfy.chilindoweather.ui.common.CommonActivity;

import java.util.Locale;


public class WeatherActivity extends CommonActivity<WeatherPresenter> implements WeatherView {
    ActivityCurrentWeatherBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_current_weather, (ViewGroup) findViewById(R.id.content_container), true);
    }

    @Override
    public void showCurrentWeather(CurrentWeather currentWeather, int preferredTemperatureIndex, int preferredSpeedIndex) {
        binding.setWeather(currentWeather);
        binding.setLocale(new Locale(getString(R.string.language)));
        binding.setPreferredTemperatureIndex(preferredTemperatureIndex);
        binding.setPreferredSpeedIndex(preferredSpeedIndex);
    }

    @Override
    public void showTitle(String title) {
        setTitle(title);
    }

    @NonNull
    @Override
    protected WeatherPresenter instantiatePresenter() {
        return new WeatherPresenter(this);
    }
}
