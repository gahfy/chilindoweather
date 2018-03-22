package net.gahfy.chilindoweather.model.weather;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiForecast;
import net.gahfy.chilindoweather.model.api.ApiForecastItem;
import net.gahfy.chilindoweather.utils.DateUtils;
import net.gahfy.chilindoweather.utils.StringUtils;
import net.gahfy.chilindoweather.utils.weather.ApiConditionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DayWeatherForecast implements Parcelable {
    public static final Creator<DayWeatherForecast> CREATOR = new Creator<DayWeatherForecast>() {
        @Override
        public DayWeatherForecast createFromParcel(Parcel in) {
            return new DayWeatherForecast(in);
        }

        @Override
        public DayWeatherForecast[] newArray(int size) {
            return new DayWeatherForecast[size];
        }
    };
    private Integer dayTimestamp;
    private String city;
    private List<InstantWeatherForecast> forecastList;

    public DayWeatherForecast(Integer dayTimestamp, List<InstantWeatherForecast> forecastList, String city) {
        this.dayTimestamp = dayTimestamp;
        this.forecastList = forecastList;
        this.city = city;
    }

    protected DayWeatherForecast(Parcel in) {
        if (in.readByte() == 0) {
            dayTimestamp = null;
        } else {
            dayTimestamp = in.readInt();
        }
        if (in.readByte() == 0) {
            city = null;
        } else {
            city = in.readString();
        }
        forecastList = in.createTypedArrayList(InstantWeatherForecast.CREATOR);
    }

    public static ArrayList<DayWeatherForecast> getDayWeatherForecastList(ApiForecast apiForecast) {
        String city = apiForecast.getCity() == null ? null : apiForecast.getCity().getName();
        ArrayList<DayWeatherForecast> dayWeatherForecasts = new ArrayList<>();
        if (apiForecast.getForecastItemList() != null) {
            for (ApiForecastItem apiForecastItem : apiForecast.getForecastItemList()) {
                Integer calculationTimestamp = apiForecastItem.getCalculationTimestamp();
                int iconResId = ApiConditionUtils.getIconResId(apiForecastItem.getCondition());
                int conditionDescription = ApiConditionUtils.getDescriptionResId(apiForecastItem.getCondition());
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
                    DayWeatherForecast dayWeatherForecast = new DayWeatherForecast(midnightTimestamp, forecastList, city);
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (dayTimestamp == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(dayTimestamp);
        }
        if (city == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeString(city);
        }
        dest.writeTypedList(forecastList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Integer getDayTimestamp() {
        return dayTimestamp;
    }

    public List<InstantWeatherForecast> getForecastList() {
        return forecastList;
    }

    public String getCity() {
        return city;
    }

    @NonNull
    public String getCalculationDateTime(@NonNull final Context context) {
        if (dayTimestamp != null) {
            return StringUtils.formatDateWithLongWeekDay(
                    context.getResources().getConfiguration().locale,
                    context.getString(R.string.date_format_forecast),
                    dayTimestamp,
                    context.getResources().getStringArray(R.array.week_days),
                    context.getResources().getStringArray(R.array.months)
            );
        }
        return context.getString(R.string.empty);
    }
}
