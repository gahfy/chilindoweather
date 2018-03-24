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

public final class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {
    private static final int VIEW_TYPE_DAY = 0;
    private static final int VIEW_TYPE_FORECAST = 1;

    private final Context context;
    private final List<Object> itemWrapper = new ArrayList<>();
    private int temperatureIndex;
    private int speedIndex;

    ForecastAdapter(final Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public final ForecastViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        if (viewType == VIEW_TYPE_DAY) {
            ItemForecastDayBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_forecast_day, parent, false);
            return new ForecastDayViewHolder(binding);
        } else {
            ItemWeatherForecastBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_weather_forecast, parent, false);
            return new ForecastInstantViewHolder(binding);
        }
    }

    final void setDayWeatherForecastList(@NonNull final List<DayWeatherForecast> dayWeatherForecastList, final int temperatureIndex, final int speedIndex) {
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
    public final void onBindViewHolder(@NonNull final ForecastViewHolder holder, final int position) {
        if (getItemViewType(position) == VIEW_TYPE_DAY) {
            holder.bind((DayWeatherForecast) itemWrapper.get(position));
        } else {
            holder.bind((InstantWeatherForecast) itemWrapper.get(position), this.temperatureIndex, this.speedIndex);
        }
    }

    @Override
    public final int getItemViewType(final int position) {
        return (itemWrapper.get(position) instanceof DayWeatherForecast) ? VIEW_TYPE_DAY : VIEW_TYPE_FORECAST;
    }

    @Override
    public final int getItemCount() {
        return itemWrapper.size();
    }

    abstract static class ForecastViewHolder extends RecyclerView.ViewHolder {
        ForecastViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding.getRoot());
        }

        void bind(@NonNull final DayWeatherForecast dayWeatherForecast) {
        }

        void bind(@NonNull final InstantWeatherForecast instantWeatherForecast, final int temperatureIndex, final int speedIndex) {
        }
    }

    private static final class ForecastDayViewHolder extends ForecastViewHolder {
        private final ItemForecastDayBinding itemForecastDayBinding;

        ForecastDayViewHolder(ViewDataBinding binding) {
            super(binding);
            itemForecastDayBinding = (ItemForecastDayBinding) binding;
        }

        @Override
        public void bind(@NonNull final DayWeatherForecast dayWeatherForecast) {
            itemForecastDayBinding.setDayWeatherForecast(dayWeatherForecast);
        }
    }

    private static final class ForecastInstantViewHolder extends ForecastViewHolder {
        private final ItemWeatherForecastBinding itemWeatherForecastBinding;

        private ForecastInstantViewHolder(@NonNull final ViewDataBinding binding) {
            super(binding);
            itemWeatherForecastBinding = (ItemWeatherForecastBinding) binding;
        }

        @Override
        public void bind(@NonNull final InstantWeatherForecast forecast, final int temperatureIndex, final int speedIndex) {
            itemWeatherForecastBinding.setForecast(forecast);
            itemWeatherForecastBinding.setPreferredTemperatureIndex(temperatureIndex);
            itemWeatherForecastBinding.setPreferredSpeedIndex(speedIndex);
            itemWeatherForecastBinding.setLocale(new Locale(itemWeatherForecastBinding.getRoot().getContext().getString(R.string.language)));
        }
    }
}
