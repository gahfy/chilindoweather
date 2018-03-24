package net.gahfy.chilindoweather.ui.weather;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.databinding.ActivityCurrentWeatherBinding;
import net.gahfy.chilindoweather.model.weather.CurrentWeather;
import net.gahfy.chilindoweather.ui.common.CommonActivity;

import java.util.Locale;

// Safe as this issue is due to AppCompatActivity
@java.lang.SuppressWarnings("squid:MaximumInheritanceDepth")
public final class WeatherActivity extends CommonActivity<WeatherPresenter> implements WeatherView {
    private ActivityCurrentWeatherBinding binding;

    @Override
    public final void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_current_weather, (ViewGroup) findViewById(R.id.content_container), true);
    }

    @Override
    public final void showCurrentWeather(@NonNull final CurrentWeather currentWeather, final int preferredTemperatureIndex, final int preferredSpeedIndex) {
        binding.setWeather(currentWeather);
        binding.setLocale(new Locale(getString(R.string.language)));
        binding.setPreferredTemperatureIndex(preferredTemperatureIndex);
        binding.setPreferredSpeedIndex(preferredSpeedIndex);
    }

    @NonNull
    @Override
    protected final WeatherPresenter instantiatePresenter() {
        return new WeatherPresenter(this);
    }
}
