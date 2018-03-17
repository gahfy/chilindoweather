package net.gahfy.chilindoweather.ui.weather;


import android.location.Location;
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

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public final class WeatherPresenter extends CommonPresenter<WeatherView> {

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

    private Disposable disposable = null;

    /**
     * Instantiates a new WeatherPresenter and inject required dependencies.
     *
     * @param view the view associated to the presenter to set
     */
    WeatherPresenter(@NonNull WeatherView view) {
        super(view);
    }

    @Override
    protected void onViewDestroyed() {
        super.onViewDestroyed();
        if (disposable != null) {
            disposable.dispose();
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
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ApiWeather>() {
                    @Override
                    public void accept(ApiWeather apiWeather) throws Exception {
                        CurrentWeather currentWeather = new CurrentWeather((apiWeather));
                        view.showCurrentWeather(currentWeather);
                        view.showTitle(currentWeather.getCity());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("gahfy", Log.getStackTraceString(throwable));
                    }
                });
    }
}
