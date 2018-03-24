package net.gahfy.chilindoweather.ui.forecast;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.databinding.ItemForecastDayBinding;
import net.gahfy.chilindoweather.databinding.ItemWeatherForecastBinding;
import net.gahfy.chilindoweather.model.weather.DayWeatherForecast;
import net.gahfy.chilindoweather.model.weather.InstantWeatherForecast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {
    private static final int VIEW_TYPE_DAY = 0;
    private static final int VIEW_TYPE_FORECAST = 1;

    private final Context context;
    private final List<Object> itemWrapper = new ArrayList<>();
    private int temperatureIndex;
    private int speedIndex;

    ForecastAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DAY) {
            ItemForecastDayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_forecast_day, parent, false);
            return new ForecastDayViewHolder(binding);
        } else {
            ItemWeatherForecastBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_weather_forecast, parent, false);
            return new ForecastInstantViewHolder(binding);
        }
    }

    void setDayWeatherForecastList(List<DayWeatherForecast> dayWeatherForecastList, int temperatureIndex, int speedIndex) {
        itemWrapper.clear();
        for (DayWeatherForecast dayWeatherForecast : dayWeatherForecastList) {
            itemWrapper.add(dayWeatherForecast);
            itemWrapper.addAll(dayWeatherForecast.getForecastList());
        }
        this.temperatureIndex = temperatureIndex;
        this.speedIndex = speedIndex;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_DAY) {
            holder.bind((DayWeatherForecast) itemWrapper.get(position));
        } else {
            holder.bind((InstantWeatherForecast) itemWrapper.get(position), this.temperatureIndex, this.speedIndex);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (itemWrapper.get(position) instanceof DayWeatherForecast) ? VIEW_TYPE_DAY : VIEW_TYPE_FORECAST;
    }

    @Override
    public int getItemCount() {
        return itemWrapper.size();
    }

    abstract static class ForecastViewHolder extends RecyclerView.ViewHolder {
        ForecastViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
        }

        void bind(DayWeatherForecast dayWeatherForecast) {
        }

        void bind(InstantWeatherForecast instantWeatherForecast, int temperatureIndex, int speedIndex) {
        }
    }

    private static class ForecastDayViewHolder extends ForecastViewHolder {
        private final ItemForecastDayBinding itemForecastDayBinding;

        ForecastDayViewHolder(ViewDataBinding binding) {
            super(binding);
            itemForecastDayBinding = (ItemForecastDayBinding) binding;
        }

        @Override
        public void bind(DayWeatherForecast dayWeatherForecast) {
            itemForecastDayBinding.setDayWeatherForecast(dayWeatherForecast);
        }
    }

    private static class ForecastInstantViewHolder extends ForecastViewHolder {
        private final ItemWeatherForecastBinding itemWeatherForecastBinding;

        private ForecastInstantViewHolder(ViewDataBinding binding) {
            super(binding);
            itemWeatherForecastBinding = (ItemWeatherForecastBinding) binding;
        }

        @Override
        public void bind(InstantWeatherForecast forecast, int temperatureIndex, int speedIndex) {
            itemWeatherForecastBinding.setForecast(forecast);
            itemWeatherForecastBinding.setPreferredTemperatureIndex(temperatureIndex);
            itemWeatherForecastBinding.setPreferredSpeedIndex(speedIndex);
            itemWeatherForecastBinding.setLocale(new Locale(itemWeatherForecastBinding.getRoot().getContext().getString(R.string.language)));
        }
    }
}
