package net.gahfy.chilindoweather.ui.common;

import android.app.Activity;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.ui.forecast.ForecastActivity;
import net.gahfy.chilindoweather.ui.forecast.ForecastPresenter;
import net.gahfy.chilindoweather.ui.forecast.ForecastView;
import net.gahfy.chilindoweather.ui.settings.SettingsActivity;
import net.gahfy.chilindoweather.ui.settings.SettingsPresenter;
import net.gahfy.chilindoweather.ui.settings.SettingsView;
import net.gahfy.chilindoweather.ui.weather.WeatherPresenter;
import net.gahfy.chilindoweather.ui.weather.WeatherView;
import net.gahfy.chilindoweather.utils.location.LocationUtils;
import net.gahfy.chilindoweather.utils.permissions.PermissionUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.Executor;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CommonPresenterTest {
    private CommonView commonView;
    private CommonPresenter<CommonView> commonPresenter;
    private GoogleSignInAccount googleSignInAccount = null;
    private PermissionUtils permissionUtils = null;
    private boolean needGeolocation = false;
    private Task<Void> googleSignOutTask = new Task<Void>() {
        @Override
        public boolean isComplete() {
            return true;
        }

        @Override
        public boolean isSuccessful() {
            return true;
        }

        @Override
        public Void getResult() {
            return null;
        }

        @Override
        public <X extends Throwable> Void getResult(@NonNull Class<X> aClass) throws X {
            return null;
        }

        @Nullable
        @Override
        public Exception getException() {
            return null;
        }

        @NonNull
        @Override
        public Task<Void> addOnSuccessListener(@NonNull OnSuccessListener<? super Void> onSuccessListener) {
            onSuccessListener.onSuccess(null);
            return this;
        }

        @NonNull
        @Override
        public Task<Void> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super Void> onSuccessListener) {
            onSuccessListener.onSuccess(null);
            return this;
        }

        @NonNull
        @Override
        public Task<Void> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super Void> onSuccessListener) {
            onSuccessListener.onSuccess(null);
            return this;
        }

        @NonNull
        @Override
        public Task<Void> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
            onFailureListener.onFailure(new Exception());
            return this;
        }

        @NonNull
        @Override
        public Task<Void> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
            onFailureListener.onFailure(new Exception());
            return this;
        }

        @NonNull
        @Override
        public Task<Void> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
            onFailureListener.onFailure(new Exception());
            return this;
        }
    };

    @Before
    public void setUp() {
        commonView = Mockito.mock(CommonView.class);
        commonPresenter = new CommonPresenter<CommonView>(commonView) {
            @Override
            protected GoogleSignInClient getGoogleSignInClient() {
                GoogleSignInClient googleSignInClient = Mockito.mock(GoogleSignInClient.class);
                Mockito.when(googleSignInClient.signOut()).thenReturn(googleSignOutTask);
                return googleSignInClient;
            }

            @Override
            protected PermissionUtils getPermissionUtils() {
                return permissionUtils;
            }

            @Override
            protected GoogleSignInAccount getGoogleSignInAccount() {
                return googleSignInAccount;
            }

            @Nullable
            @Override
            protected LocationUtils getLocationUtils() {
                return null;
            }

            protected boolean needGeolocationonStartup() {
                return needGeolocation;
            }

            protected void onLocationAvailable(Location location) {
            }
        };
    }

    @Test
    public void testViewCreatedWithExistingAccount() throws Exception {
        googleSignInAccount = Mockito.mock(GoogleSignInAccount.class);
        Mockito.when(googleSignInAccount.getDisplayName()).thenReturn("Gaëtan HERFRAY");
        Mockito.when(googleSignInAccount.getEmail()).thenReturn("gaetan.hfy@gmail.com");
        Mockito.when(googleSignInAccount.getPhotoUrl()).thenReturn(Uri.parse("http://www.google.com/"));
        commonPresenter.onViewCreated();
        verify(commonView, times(1)).showProfileInfo(Uri.parse("http://www.google.com"), "Gaëtan HERFRAY", "gaetan.hfy@gmail.com");
        verify(commonView, times(1)).setMenuVisibility(R.id.sign_in, false);
        verify(commonView, times(1)).setMenuVisibility(R.id.sign_out, true);
    }

    @Test
    public void testViewCreatedWithoutExistingAccount() throws Exception {
        googleSignInAccount = null;
        commonPresenter.onViewCreated();
        verify(commonView, times(1)).removeUserInfo();
        verify(commonView, times(1)).setMenuVisibility(R.id.sign_in, true);
        verify(commonView, times(1)).setMenuVisibility(R.id.sign_out, false);
    }

    @Test
    public void testViewCreatedGeolocationNotRequired() {
        needGeolocation = false;
        permissionUtils = Mockito.mock(PermissionUtils.class);
        commonPresenter.onViewCreated();
        verify(permissionUtils, times(0)).hasFineLocationPermission();
    }

    @Test
    public void testViewCreatedGeolocationRequired() {
        needGeolocation = true;
        permissionUtils = Mockito.mock(PermissionUtils.class);
        commonPresenter.onViewCreated();
        verify(permissionUtils, times(1)).hasFineLocationPermission();
    }

    @Test
    public void testResumeViewMenuChecked() {
        WeatherView weatherView = Mockito.mock(WeatherView.class);
        ForecastView forecastView = Mockito.mock(ForecastView.class);
        SettingsView settingsView = Mockito.mock(SettingsView.class);

        WeatherPresenter weatherPresenter = new WeatherPresenter(weatherView);
        ForecastPresenter forecastPresenter = new ForecastPresenter(forecastView);
        SettingsPresenter settingsPresenter = new SettingsPresenter(settingsView);

        weatherPresenter.onResumeView();
        verify(weatherView, times(1)).checkMenu(R.id.current_weather);

        forecastPresenter.onResumeView();
        verify(forecastView, times(1)).checkMenu(R.id.weather_forecast);

        settingsPresenter.onResumeView();
        verify(settingsView, times(1)).checkMenu(R.id.settings);

        commonPresenter.onResumeView();
        verify(commonView, times(0)).checkMenu(anyInt());
    }

    @Test
    public void testNavigationListener() {
        WeatherView weatherView = Mockito.mock(WeatherView.class);
        ForecastView forecastView = Mockito.mock(ForecastView.class);
        SettingsView settingsView = Mockito.mock(SettingsView.class);

        MenuItem signInMenuItem = Mockito.mock(MenuItem.class);
        Mockito.when(signInMenuItem.getItemId()).thenReturn(R.id.sign_in);
        MenuItem weatherMenuItem = Mockito.mock(MenuItem.class);
        Mockito.when(weatherMenuItem.getItemId()).thenReturn(R.id.current_weather);
        MenuItem forecastMenuItem = Mockito.mock(MenuItem.class);
        Mockito.when(forecastMenuItem.getItemId()).thenReturn(R.id.weather_forecast);
        MenuItem settingsMenuItem = Mockito.mock(MenuItem.class);
        Mockito.when(settingsMenuItem.getItemId()).thenReturn(R.id.settings);
        MenuItem signOutMenuItem = Mockito.mock(MenuItem.class);
        Mockito.when(signOutMenuItem.getItemId()).thenReturn(R.id.sign_out);

        WeatherPresenter weatherPresenter = new WeatherPresenter(weatherView);
        ForecastPresenter forecastPresenter = new ForecastPresenter(forecastView);
        SettingsPresenter settingsPresenter = new SettingsPresenter(settingsView);

        NavigationView.OnNavigationItemSelectedListener weatherNavigationListener = weatherPresenter.getOnNavigationItemSelectedListener();
        NavigationView.OnNavigationItemSelectedListener forecastNavigationListener = forecastPresenter.getOnNavigationItemSelectedListener();
        NavigationView.OnNavigationItemSelectedListener settingsNavigationListener = settingsPresenter.getOnNavigationItemSelectedListener();

        weatherNavigationListener.onNavigationItemSelected(signInMenuItem);
        Mockito.verify(weatherView, times(1)).closeDrawers();
        Mockito.verify(weatherView, times(1)).checkMenu(R.id.current_weather);

        // Unfortunately NPE => https://stackoverflow.com/questions/49343647/mockito-when-and-googlesigninclient-nullpointerexception
        //weatherNavigationListener.onNavigationItemSelected(signOutMenuItem);
        //Mockito.verify(weatherView, times(2)).closeDrawers();
        //Mockito.verify(weatherView, times(2)).checkMenu(R.id.current_weather);

        weatherNavigationListener.onNavigationItemSelected(weatherMenuItem);
        Mockito.verify(weatherView, times(0)).finish();

        weatherNavigationListener.onNavigationItemSelected(forecastMenuItem);
        Mockito.verify(weatherView, times(1)).startActivity(ForecastActivity.class);

        weatherNavigationListener.onNavigationItemSelected(settingsMenuItem);
        Mockito.verify(weatherView, times(1)).startActivity(SettingsActivity.class);

        forecastNavigationListener.onNavigationItemSelected(weatherMenuItem);
        Mockito.verify(forecastView, times(1)).finish();

        forecastNavigationListener.onNavigationItemSelected(forecastMenuItem);
        Mockito.verify(forecastView, times(0)).startActivity(ForecastActivity.class);

        settingsNavigationListener.onNavigationItemSelected(settingsMenuItem);
        Mockito.verify(settingsView, times(0)).startActivity(SettingsActivity.class);
    }
}
