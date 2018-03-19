package net.gahfy.chilindoweather.ui.forecast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.weather.DayWeatherForecast;
import net.gahfy.chilindoweather.ui.common.CommonActivity;

import java.util.List;

public class ForecastActivity extends CommonActivity<ForecastPresenter> implements ForecastView {
    private ForecastAdapter forecastAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LayoutInflater.from(this).inflate(R.layout.activity_forecast, (ViewGroup) findViewById(R.id.content_container), true);
        forecastAdapter = new ForecastAdapter(this);

        RecyclerView forecastList = findViewById(R.id.weather_forecast_list);
        forecastList.setLayoutManager(new LinearLayoutManager(this));
        forecastList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        forecastList.setAdapter(forecastAdapter);
    }

    @Override
    public void setDayWeatherForecastList(List<DayWeatherForecast> dayWeatherForecastList, int temperatureIndex, int speedIndex) {
        forecastAdapter.setDayWeatherForecastList(dayWeatherForecastList, temperatureIndex, speedIndex);
    }

    @NonNull
    @Override
    protected ForecastPresenter instantiatePresenter() {
        return new ForecastPresenter(this);
    }
}
