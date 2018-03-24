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
import java.util.List;

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
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    GoogleSignInClient googleSignInClient;

    @Inject
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    PermissionUtils permissionUtils;

    @Inject
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    LocationUtils locationUtils;

    @Inject
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    PreferencesUtils preferencesUtils;

    private List<DayWeatherForecast> dayWeatherForecastList = null;

    private Disposable disposable = null;

    ForecastPresenter(@NonNull final ForecastView view) {
        super(view);
    }

    // Safe as all Non Null properties are injected
    @SuppressWarnings("squid:S2637")
    @Override
    public final void onViewCreated(@Nullable final Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(FORECAST_KEY)) {
            dayWeatherForecastList = savedInstanceState.getParcelableArrayList(FORECAST_KEY);
        }
    }

    @Override
    public final void saveInstanceState(@NonNull final Bundle outState) {
        ArrayList<DayWeatherForecast> dayWeatherForecasts = new ArrayList<>();
        dayWeatherForecasts.addAll(this.dayWeatherForecastList);
        outState.putParcelableArrayList(FORECAST_KEY, dayWeatherForecasts);
    }

    @Override
    public final void onViewDestroyed() {
        super.onViewDestroyed();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public final void onResumeView() {
        super.onResumeView();
        if (dayWeatherForecastList != null && !dayWeatherForecastList.isEmpty()) {
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
    @NonNull
    protected final GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    @Override
    @NonNull
    protected final PermissionUtils getPermissionUtils() {
        return permissionUtils;
    }

    @Override
    @Nullable
    protected final GoogleSignInAccount getGoogleSignInAccount() {
        return googleSignInAccount;
    }

    @Override
    @NonNull
    protected final LocationUtils getLocationUtils() {
        return locationUtils;
    }

    @Override
    protected final boolean needGeolocationonStartup() {
        return true;
    }

    @Override
    protected final void onLocationAvailable(@NonNull final Location location) {
        callForecastApi(location);
    }

    private void callForecastApi(@NonNull final Location location) {
        view.showLoading(R.string.loading_network);
        disposable = openWeatherMapApi.getForecast(location.getLatitude(), location.getLongitude())
                .flatMap(new Function<ApiForecast, ObservableSource<List<DayWeatherForecast>>>() {
                    @Override
                    public ObservableSource<List<DayWeatherForecast>> apply(ApiForecast apiForecast) throws Exception {
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
                .subscribe(new Consumer<List<DayWeatherForecast>>() {
                    @Override
                    public void accept(List<DayWeatherForecast> dayWeatherForecastList) throws Exception {
                        updateDayWeatherForecastList(dayWeatherForecastList);
                        if (!dayWeatherForecastList.isEmpty() && dayWeatherForecastList.get(0).getCity() != null) {
                            view.setTitle(R.string.forecast_for_title, dayWeatherForecastList.get(0).getCity());
                        } else {
                            view.setTitle(R.string.forecast_title);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideContent();
                        view.showNetworkError(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                callForecastApi(location);
                            }
                        });
                    }
                });
    }

    private void updateDayWeatherForecastList(@NonNull final List<DayWeatherForecast> dayWeatherForecastList) {
        view.showContent();
        this.dayWeatherForecastList = dayWeatherForecastList;
        view.setDayWeatherForecastList(dayWeatherForecastList, preferencesUtils.getTemperatureIndex(), preferencesUtils.getWindSpeedIndex());
    }
}
