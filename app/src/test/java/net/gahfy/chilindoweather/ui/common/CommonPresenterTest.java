package net.gahfy.chilindoweather.ui.common;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import junit.framework.Assert;

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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.Executor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CommonPresenterTest {
    private CommonView commonView;
    private CommonPresenter<CommonView> commonPresenter;
    private GoogleSignInAccount googleSignInAccount = null;
    private PermissionUtils permissionUtils = null;
    private GoogleSignInResult googleSignInResult = null;
    private boolean needGeolocation = false;
    private LocationUtils locationUtils = null;
    private int onLocationAvailableCounter;
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
        public Task<Void> addOnCompleteListener(@NonNull OnCompleteListener<Void> onCompleteListener) {
            onCompleteListener.onComplete(this);
            return this;
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

            @Override
            protected GoogleSignInResult getSignInResultFromIntent(Intent data) {
                return googleSignInResult;
            }

            @Nullable
            @Override
            protected LocationUtils getLocationUtils() {
                return locationUtils;
            }

            protected boolean needGeolocationonStartup() {
                return needGeolocation;
            }

            protected void onLocationAvailable(Location location) {
                onLocationAvailableCounter++;
            }
        };
    }

    @Test
    public void testViewResumeWithExistingAccount() throws Exception {
        googleSignInAccount = Mockito.mock(GoogleSignInAccount.class);
        Mockito.when(googleSignInAccount.getDisplayName()).thenReturn("Gaëtan HERFRAY");
        Mockito.when(googleSignInAccount.getEmail()).thenReturn("gaetan.hfy@gmail.com");
        Mockito.when(googleSignInAccount.getPhotoUrl()).thenReturn(Uri.parse("http://www.google.com/"));
        commonPresenter.onResumeView();
        verify(commonView, times(1)).showProfileInfo(Uri.parse("http://www.google.com"), "Gaëtan HERFRAY", "gaetan.hfy@gmail.com");
        verify(commonView, times(1)).setMenuVisibility(R.id.sign_in, false);
        verify(commonView, times(1)).setMenuVisibility(R.id.sign_out, true);
    }

    @Test
    public void testViewResumeWithoutExistingAccount() throws Exception {
        googleSignInAccount = null;
        commonPresenter.onResumeView();
        verify(commonView, times(1)).removeUserInfo();
        verify(commonView, times(1)).setMenuVisibility(R.id.sign_in, true);
        verify(commonView, times(1)).setMenuVisibility(R.id.sign_out, false);
    }

    @Test
    public void testViewCreatedGeolocationNotRequired() {
        needGeolocation = false;
        permissionUtils = Mockito.mock(PermissionUtils.class);
        commonPresenter.onViewCreated(null);
        verify(permissionUtils, times(0)).hasFineLocationPermission();
    }

    @Test
    public void testViewCreatedGeolocationRequired() {
        needGeolocation = true;
        permissionUtils = Mockito.mock(PermissionUtils.class);
        commonPresenter.onViewCreated(null);
        verify(permissionUtils, times(1)).hasFineLocationPermission();
    }

    @Test
    public void testViewCreatedGeolocate() {
        needGeolocation = true;
        permissionUtils = Mockito.mock(PermissionUtils.class);
        Mockito.when(permissionUtils.hasFineLocationPermission()).thenReturn(true);

        locationUtils = Mockito.mock(LocationUtils.class);
        Mockito.when(locationUtils.addSingleLocationListener(any(LocationUtils.SingleLocationListener.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                LocationUtils.SingleLocationListener singleLocationListener = invocation.getArgument(0);
                singleLocationListener.onLocationFound(Mockito.mock(Location.class));
                return null;
            }
        });
        commonPresenter.onViewCreated(null);
        Assert.assertEquals(1, onLocationAvailableCounter);

        Mockito.when(locationUtils.addSingleLocationListener(any(LocationUtils.SingleLocationListener.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                LocationUtils.SingleLocationListener singleLocationListener = invocation.getArgument(0);
                singleLocationListener.onProviderNotAvailableError();
                return null;
            }
        });
        commonPresenter.onViewCreated(null);
        Mockito.verify(commonView, times(1)).showNoGeolocationAvailableError(any(View.OnClickListener.class));

        Mockito.when(locationUtils.addSingleLocationListener(any(LocationUtils.SingleLocationListener.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                Mockito.when(permissionUtils.hasFineLocationPermission()).thenReturn(false);
                LocationUtils.SingleLocationListener singleLocationListener = invocation.getArgument(0);
                singleLocationListener.onPermissionError();
                return null;
            }
        });
        commonPresenter.onViewCreated(null);
        Mockito.verify(permissionUtils, times(1)).requestGeolocationPermission(anyInt());
    }

    @Test
    public void testViewCreatedGeolocationRequiredNoPermission() {
        needGeolocation = true;
        permissionUtils = Mockito.mock(PermissionUtils.class);
        Mockito.when(permissionUtils.hasFineLocationPermission()).thenReturn(false);

        Mockito.when(permissionUtils.shouldShowGeolocationRationale()).thenReturn(false);
        commonPresenter.onViewCreated(null);
        verify(permissionUtils, times(1)).requestGeolocationPermission(1);

        Mockito.when(permissionUtils.shouldShowGeolocationRationale()).thenReturn(true);
        commonPresenter.onViewCreated(null);
        verify(commonView, times(1)).showGeolocationPermissionRationale(any(View.OnClickListener.class));
    }

    @Test
    public void testViewCreatedGeolocationRequiredWithPermission() {
        needGeolocation = true;
        permissionUtils = Mockito.mock(PermissionUtils.class);
        Mockito.when(permissionUtils.hasFineLocationPermission()).thenReturn(true);

        commonPresenter.onViewCreated(null);
        verify(commonView, times(1)).hideGeolocationRationale();
    }

    @Test
    public void testOnPermissionResult() {
        permissionUtils = Mockito.mock(PermissionUtils.class);
        Mockito.when(commonView.showGeolocationPermissionRationale(any(View.OnClickListener.class))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                View.OnClickListener onClickListener = invocation.getArgument(0);
                onClickListener.onClick(null);
                return null;
            }
        });

        commonPresenter.onPermissionResult(10, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, new int[]{0});
        Mockito.verify(commonView, times(1)).showGeolocationPermissionRationale(any(View.OnClickListener.class));
        Mockito.verify(permissionUtils, times(1)).requestGeolocationPermission(anyInt());

        commonPresenter.onPermissionResult(1, new String[0], new int[0]);
        Mockito.verify(commonView, times(2)).showGeolocationPermissionRationale(any(View.OnClickListener.class));
        Mockito.verify(permissionUtils, times(2)).requestGeolocationPermission(anyInt());

        commonPresenter.onPermissionResult(1, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, new int[]{1});
        Mockito.verify(commonView, times(3)).showGeolocationPermissionRationale(any(View.OnClickListener.class));
        Mockito.verify(permissionUtils, times(3)).requestGeolocationPermission(anyInt());

        commonPresenter.onPermissionResult(1, new String[]{"android.permission.ACCESS_FINE_LOCATION"}, new int[]{0});
        Mockito.verify(commonView, times(1)).hideGeolocationRationale();
    }

    @Test
    public void testOnActivityResult() {
        googleSignInAccount = Mockito.mock(GoogleSignInAccount.class);
        Mockito.when(googleSignInAccount.getDisplayName()).thenReturn("Gaëtan HERFRAY");
        Mockito.when(googleSignInAccount.getEmail()).thenReturn("gaetan.hfy@gmail.com");
        Mockito.when(googleSignInAccount.getPhotoUrl()).thenReturn(Uri.parse("http://www.google.com/"));
        googleSignInResult = Mockito.mock(GoogleSignInResult.class);

        Mockito.when(googleSignInResult.isSuccess()).thenReturn(true);
        Mockito.when(googleSignInResult.getSignInAccount()).thenReturn(googleSignInAccount);
        commonPresenter.onActivityResult(2, 0, new Intent());
        Mockito.verify(commonView, times(1)).showProfileInfo(Uri.parse("http://www.google.com"), "Gaëtan HERFRAY", "gaetan.hfy@gmail.com");

        Mockito.when(googleSignInResult.isSuccess()).thenReturn(false);
        commonPresenter.onActivityResult(2, 0, new Intent());
        Mockito.verify(commonView, times(1)).removeUserInfo();

        Mockito.when(googleSignInResult.isSuccess()).thenReturn(true);
        Mockito.when(googleSignInResult.getSignInAccount()).thenReturn(googleSignInAccount);
        commonPresenter.onActivityResult(10, 0, new Intent());
        Mockito.verify(commonView, times(1)).removeUserInfo();
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
    public void testOnViewDestroyed() {
        locationUtils = null;
        // Assume no Exception
        commonPresenter.onViewDestroyed();

        locationUtils = Mockito.mock(LocationUtils.class);
        commonPresenter.onViewDestroyed();
        verify(locationUtils, times(1)).removeSingleLocationListener(any(LocationUtils.SingleLocationListener.class));
    }

    @Test
    public void testNavigationListener() throws Exception {
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
        MenuItem fantasyMenuItem = Mockito.mock(MenuItem.class);
        Mockito.when(fantasyMenuItem.getItemId()).thenReturn(1000);

        WeatherPresenter weatherPresenter = new WeatherPresenter(weatherView);
        ForecastPresenter forecastPresenter = new ForecastPresenter(forecastView);
        SettingsPresenter settingsPresenter = new SettingsPresenter(settingsView);

        NavigationView.OnNavigationItemSelectedListener commonNavigationListener = commonPresenter.getOnNavigationItemSelectedListener();
        NavigationView.OnNavigationItemSelectedListener weatherNavigationListener = weatherPresenter.getOnNavigationItemSelectedListener();
        NavigationView.OnNavigationItemSelectedListener forecastNavigationListener = forecastPresenter.getOnNavigationItemSelectedListener();
        NavigationView.OnNavigationItemSelectedListener settingsNavigationListener = settingsPresenter.getOnNavigationItemSelectedListener();

        weatherNavigationListener.onNavigationItemSelected(signInMenuItem);
        Mockito.verify(weatherView, times(1)).closeDrawers();
        Mockito.verify(weatherView, times(1)).checkMenu(R.id.current_weather);

        commonNavigationListener.onNavigationItemSelected(signOutMenuItem);
        Mockito.verify(commonView, times(1)).removeUserInfo();

        weatherNavigationListener.onNavigationItemSelected(weatherMenuItem);
        Mockito.verify(weatherView, times(0)).finish();

        weatherNavigationListener.onNavigationItemSelected(forecastMenuItem);
        Mockito.verify(weatherView, times(1)).startActivity(ForecastActivity.class);

        weatherNavigationListener.onNavigationItemSelected(settingsMenuItem);
        Mockito.verify(weatherView, times(1)).startActivity(SettingsActivity.class);

        forecastNavigationListener.onNavigationItemSelected(forecastMenuItem);
        Mockito.verify(forecastView, times(1)).startActivity(ForecastActivity.class);

        settingsNavigationListener.onNavigationItemSelected(settingsMenuItem);
        Mockito.verify(settingsView, times(1)).startActivity(SettingsActivity.class);

        settingsNavigationListener.onNavigationItemSelected(fantasyMenuItem);
        Mockito.verify(settingsView, times(1)).startActivity(SettingsActivity.class);
        Mockito.verify(settingsView, times(0)).startActivity(ForecastActivity.class);
    }
}
