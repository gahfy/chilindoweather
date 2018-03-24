package net.gahfy.chilindoweather.ui.weather;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiWeather;
import net.gahfy.chilindoweather.model.weather.CurrentWeather;
import net.gahfy.chilindoweather.network.OpenWeatherMapApi;
import net.gahfy.chilindoweather.ui.common.CommonPresenter;
import net.gahfy.chilindoweather.utils.LocationUtils;
import net.gahfy.chilindoweather.utils.PermissionUtils;
import net.gahfy.chilindoweather.utils.PreferencesUtils;
import net.gahfy.chilindoweather.utils.Schedulers;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public final class WeatherPresenter extends CommonPresenter<WeatherView> {
    private static final String CURRENT_WEATHER_KEY = "currentWeather";

    @Inject
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    OpenWeatherMapApi openWeatherMapApi;
    @Inject
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    PermissionUtils permissionUtils;
    @Inject
    LocationUtils locationUtils;
    @Inject
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    GoogleApiClient googleApiClient;
    @Inject
    @NonNull
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    GoogleSignInClient googleSignInClient;
    @Inject
    @Nullable
    GoogleSignInAccount googleSignInAccount;
    @Inject
    PreferencesUtils preferencesUtils;
    private CurrentWeather currentWeather = null;
    private Disposable disposable = null;

    /**
     * Instantiates a new WeatherPresenter and inject required dependencies.
     *
     * @param view the view associated to the presenter to set
     */
    @SuppressWarnings("squid:S2637")
    public WeatherPresenter(@NonNull WeatherView view) {
        super(view);
    }

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.containsKey(CURRENT_WEATHER_KEY)) {
            currentWeather = savedInstanceState.getParcelable(CURRENT_WEATHER_KEY);
        }
    }

    @Override
    public void saveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_WEATHER_KEY, currentWeather);
    }

    @Override
    public void onViewDestroyed() {
        super.onViewDestroyed();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void onResumeView() {
        super.onResumeView();
        if (currentWeather != null) {
            view.showCurrentWeather(currentWeather, preferencesUtils.getTemperatureIndex(), preferencesUtils.getWindSpeedIndex());
            view.showContent();
            view.setTitle(R.string.weather_at_title, currentWeather.getCity());
            view.hideLoading();
        } else {
            view.setTitle(R.string.weather_title);
            if (locationUtils.hasLocationAvailable()) {
                view.showLoading(R.string.loading_network);
            }
        }
    }

    @Override
    @NonNull
    protected GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    @Override
    @NonNull
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
    protected void onLocationAvailable(@NonNull final Location location) {
        callWeatherApi(location);
    }

    private void callWeatherApi(@NonNull final Location location) {
        view.showLoading(R.string.loading_network);
        disposable = openWeatherMapApi.getWeather(location.getLatitude(), location.getLongitude())
                .flatMap(new Function<ApiWeather, ObservableSource<CurrentWeather>>() {
                    @Override
                    public ObservableSource<CurrentWeather> apply(ApiWeather apiWeather) throws Exception {
                        return Observable.just(new CurrentWeather(apiWeather));
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
                .subscribe(new Consumer<CurrentWeather>() {
                    @Override
                    public void accept(CurrentWeather currentWeather) throws Exception {
                        WeatherPresenter.this.currentWeather = currentWeather;
                        view.showContent();
                        view.showCurrentWeather(currentWeather, preferencesUtils.getTemperatureIndex(), preferencesUtils.getWindSpeedIndex());
                        view.setTitle(R.string.weather_at_title, currentWeather.getCity());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.hideContent();
                        view.showNetworkError(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                callWeatherApi(location);
                            }
                        });
                    }
                });
    }
}
