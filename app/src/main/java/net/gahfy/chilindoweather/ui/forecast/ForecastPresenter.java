package net.gahfy.chilindoweather.ui.forecast;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import net.gahfy.chilindoweather.R;
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
import io.reactivex.functions.Action;
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
        if (dayWeatherForecastList != null && dayWeatherForecastList.size() > 0) {
            updateDayWeatherForecastList(dayWeatherForecastList);
            view.setTitle(R.string.forecast_for_title, dayWeatherForecastList.get(0).getCity());
            view.hideLoading();
        } else {
            view.setTitle(R.string.forecast_title);
            if (locationUtils.hasLocationAvailable()) {
                view.showLoading(R.string.loading_network);
            }
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
    protected void onLocationAvailable(final Location location) {
        view.showLoading(R.string.loading_network);
        disposable = openWeatherMapApi.getForecast(location.getLatitude(), location.getLongitude())
                .flatMap(new Function<ApiForecast, ObservableSource<ArrayList<DayWeatherForecast>>>() {
                    @Override
                    public ObservableSource<ArrayList<DayWeatherForecast>> apply(ApiForecast apiForecast) throws Exception {
                        return Observable.just(DayWeatherForecast.getDayWeatherForecastList(apiForecast));
                    }
                })
                .observeOn(Schedulers.androidThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        view.hideLoading();
                    }
                })
                .subscribe(new Consumer<ArrayList<DayWeatherForecast>>() {
                    @Override
                    public void accept(ArrayList<DayWeatherForecast> dayWeatherForecastList) throws Exception {
                        updateDayWeatherForecastList(dayWeatherForecastList);
                        view.setTitle(R.string.forecast_for_title, dayWeatherForecastList.get(0).getCity());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideContent();
                        view.showNetworkError(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                onLocationAvailable(location);
                            }
                        });
                    }
                });
    }

    private void updateDayWeatherForecastList(ArrayList<DayWeatherForecast> dayWeatherForecastList) {
        view.showContent();
        this.dayWeatherForecastList = dayWeatherForecastList;
        view.setDayWeatherForecastList(dayWeatherForecastList, preferencesUtils.getTemperatureIndex(), preferencesUtils.getWindSpeedIndex());
    }
}
