package net.gahfy.chilindoweather.ui.forecast;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import net.gahfy.chilindoweather.model.api.ApiForecast;
import net.gahfy.chilindoweather.model.weather.DayWeatherForecast;
import net.gahfy.chilindoweather.network.OpenWeatherMapApi;
import net.gahfy.chilindoweather.ui.common.CommonPresenter;
import net.gahfy.chilindoweather.utils.location.LocationUtils;
import net.gahfy.chilindoweather.utils.permissions.PermissionUtils;
import net.gahfy.chilindoweather.utils.preferences.PreferencesUtils;
import net.gahfy.chilindoweather.utils.rxandroid.Schedulers;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class ForecastPresenter extends CommonPresenter<ForecastView> {
    private static final String FORECAST_KEY = "forecast";

    @Inject
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    OpenWeatherMapApi openWeatherMapApi;

    @Inject
    @Nullable
    GoogleSignInAccount googleSignInAccount;

    @Inject
    GoogleSignInClient googleSignInClient;

    @Inject
    PermissionUtils permissionUtils;

    @Inject
    LocationUtils locationUtils;

    @Inject
    PreferencesUtils preferencesUtils;

    private ArrayList<DayWeatherForecast> dayWeatherForecastList = null;

    private Disposable disposable = null;

    public ForecastPresenter(@NonNull ForecastView view) {
        super(view);
    }

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(FORECAST_KEY)) {
            dayWeatherForecastList = savedInstanceState.getParcelableArrayList(FORECAST_KEY);
        }
    }

    @Override
    public Bundle saveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(FORECAST_KEY, dayWeatherForecastList);
        return outState;
    }

    public void onViewDestroyed() {
        super.onViewDestroyed();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void onResumeView() {
        super.onResumeView();
        if (dayWeatherForecastList != null) {
            updateDayWeatherForecastList(dayWeatherForecastList);
        }
    }

    @Override
    protected GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    @Override
    protected PermissionUtils getPermissionUtils() {
        return permissionUtils;
    }

    @Override
    @Nullable
    protected GoogleSignInAccount getGoogleSignInAccount() {
        return googleSignInAccount;
    }

    @Override
    protected LocationUtils getLocationUtils() {
        return locationUtils;
    }

    @Override
    protected boolean needGeolocationonStartup() {
        return true;
    }

    @Override
    protected void onLocationAvailable(Location location) {
        disposable = openWeatherMapApi.getForecast(location.getLatitude(), location.getLongitude())
                .flatMap(new Function<ApiForecast, ObservableSource<ArrayList<DayWeatherForecast>>>() {
                    @Override
                    public ObservableSource<ArrayList<DayWeatherForecast>> apply(ApiForecast apiForecast) throws Exception {
                        return Observable.just(DayWeatherForecast.getDayWeatherForecastList(apiForecast));
                    }
                })
                .observeOn(Schedulers.androidThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ArrayList<DayWeatherForecast>>() {
                    @Override
                    public void accept(ArrayList<DayWeatherForecast> dayWeatherForecastList) throws Exception {
                        updateDayWeatherForecastList(dayWeatherForecastList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    private void updateDayWeatherForecastList(ArrayList<DayWeatherForecast> dayWeatherForecastList) {
        this.dayWeatherForecastList = dayWeatherForecastList;
        view.setDayWeatherForecastList(dayWeatherForecastList, preferencesUtils.getTemperatureIndex(), preferencesUtils.getWindSpeedIndex());
    }
}
