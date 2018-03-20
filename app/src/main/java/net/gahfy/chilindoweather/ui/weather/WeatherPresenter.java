package net.gahfy.chilindoweather.ui.weather;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.GoogleApiClient;

import net.gahfy.chilindoweather.model.api.ApiWeather;
import net.gahfy.chilindoweather.model.weather.CurrentWeather;
import net.gahfy.chilindoweather.network.OpenWeatherMapApi;
import net.gahfy.chilindoweather.ui.common.CommonPresenter;
import net.gahfy.chilindoweather.utils.location.LocationUtils;
import net.gahfy.chilindoweather.utils.permissions.PermissionUtils;
import net.gahfy.chilindoweather.utils.preferences.PreferencesUtils;
import net.gahfy.chilindoweather.utils.rxandroid.Schedulers;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
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
    public Bundle saveInstanceState(Bundle outState) {
        outState.putParcelable(CURRENT_WEATHER_KEY, currentWeather);
        return outState;
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
    protected void onLocationAvailable(Location location) {
        disposable = openWeatherMapApi.getWeather(location.getLatitude(), location.getLongitude())
                .flatMap(new Function<ApiWeather, ObservableSource<CurrentWeather>>() {
                    @Override
                    public ObservableSource<CurrentWeather> apply(ApiWeather apiWeather) throws Exception {
                        return Observable.just(new CurrentWeather(apiWeather));
                    }
                })
                .observeOn(Schedulers.androidThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<CurrentWeather>() {
                    @Override
                    public void accept(CurrentWeather currentWeather) throws Exception {
                        WeatherPresenter.this.currentWeather = currentWeather;
                        view.showCurrentWeather(currentWeather, preferencesUtils.getTemperatureIndex(), preferencesUtils.getWindSpeedIndex());
                        view.showTitle(currentWeather.getCity());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                        Log.e("gahfy", Log.getStackTraceString(throwable));
                    }
                });
    }
}
