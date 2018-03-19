package net.gahfy.chilindoweather.ui.common;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.ui.base.BasePresenter;
import net.gahfy.chilindoweather.ui.forecast.ForecastActivity;
import net.gahfy.chilindoweather.ui.forecast.ForecastPresenter;
import net.gahfy.chilindoweather.ui.settings.SettingsActivity;
import net.gahfy.chilindoweather.ui.settings.SettingsPresenter;
import net.gahfy.chilindoweather.ui.weather.WeatherPresenter;
import net.gahfy.chilindoweather.utils.location.LocationUtils;
import net.gahfy.chilindoweather.utils.log.Logger;
import net.gahfy.chilindoweather.utils.permissions.PermissionUtils;

public abstract class CommonPresenter<V extends CommonView> extends BasePresenter<V> {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 2;

    private final View.OnClickListener RationaleRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getPermissionUtils().requestGeolocationPermission(PERMISSION_REQUEST_CODE);
        }
    };

    private final LocationUtils.SingleLocationListener locationListener = new LocationUtils.SingleLocationListener() {
        @Override
        public void onLocationFound(Location location) {
            Logger.e(CommonPresenter.class.getSimpleName(), "Location found: " + location.getLatitude() + "," + location.getLongitude());
            onLocationAvailable(location);
        }

        @Override
        public void onProviderNotAvailableError() {
            view.showNoGeolocationAvailableError(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    view.startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
                }
            });
        }

        @Override
        public void onPermissionError() {
            manageGeolocationPermission();
        }
    };

    /**
     * Instantiates a new BasePresenter and inject required dependencies.
     *
     * @param view the view associated to the presenter to set
     */
    public CommonPresenter(@NonNull V view) {
        super(view);
    }

    public void onViewCreated() {
        if (needGeolocationonStartup()) {
            manageGeolocationPermission();
        }
    }

    public void onResumeView() {
        inject();
        checkRelevantMenu();
        presentAccount(getGoogleSignInAccount());
    }

    private void manageGeolocationPermission() {
        if (getPermissionUtils().hasFineLocationPermission()) {
            view.hideGeolocationRationale();
            geolocate();
        } else {
            if (getPermissionUtils().shouldShowGeolocationRationale()) {
                view.showGeolocationPermissionRationale(RationaleRetryClickListener);
            } else {
                getPermissionUtils().requestGeolocationPermission(PERMISSION_REQUEST_CODE);
            }
        }
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result = getSignInResultFromIntent(data);
            onGoogleSignInResult(result);
        }
    }

    protected GoogleSignInResult getSignInResultFromIntent(Intent data) {
        return Auth.GoogleSignInApi.getSignInResultFromIntent(data);
    }

    private void onSignInClick() {
        checkRelevantMenu();
        Intent signInIntent = getGoogleSignInClient().getSignInIntent();
        view.startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE);
    }

    private void onSignOutClick() {
        checkRelevantMenu();
        getGoogleSignInClient().signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                view.removeUserInfo();
                view.setMenuVisibility(R.id.sign_in, true);
                view.setMenuVisibility(R.id.sign_out, false);
            }
        });
    }

    private void onGoogleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            presentAccount(result.getSignInAccount());
        } else {
            view.removeUserInfo();
            view.showGoogleSignInError(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onSignInClick();
                }
            });
        }
    }

    // Safe as Geolocate is only called when permission is granted
    @SuppressLint("MissingPermission")
    private void geolocate() {
        if (getLocationUtils() != null) {
            getLocationUtils().addSingleLocationListener(locationListener);
        }
    }

    public void onViewDestroyed() {
        if (getLocationUtils() != null) {
            getLocationUtils().removeSingleLocationListener(locationListener);
        }
    }

    private void presentAccount(final GoogleSignInAccount account) {
        if (account != null) {
            view.setMenuVisibility(R.id.sign_in, false);
            view.setMenuVisibility(R.id.sign_out, true);
            view.showProfileInfo(account.getPhotoUrl(), account.getDisplayName(), account.getEmail());
        } else {
            view.setMenuVisibility(R.id.sign_in, true);
            view.setMenuVisibility(R.id.sign_out, false);
            view.removeUserInfo();
        }
    }

    private void checkRelevantMenu() {
        if (this instanceof WeatherPresenter) {
            view.checkMenu(R.id.current_weather);
        } else if (this instanceof SettingsPresenter) {
            view.checkMenu(R.id.settings);
        } else if (this instanceof ForecastPresenter) {
            view.checkMenu(R.id.weather_forecast);
        }
    }

    // Safe as it in use for unit tests
    @SuppressWarnings("WeakerAccess")
    public NavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                view.closeDrawers();
                switch (menuItem.getItemId()) {
                    case R.id.sign_in:
                        onSignInClick();
                        break;
                    case R.id.sign_out:
                        onSignOutClick();
                        break;
                    case R.id.current_weather:
                        if (!(CommonPresenter.this instanceof WeatherPresenter)) {
                            view.finish();
                        }
                        break;
                    case R.id.weather_forecast:
                        if (!(CommonPresenter.this instanceof ForecastPresenter)) {
                            view.startActivity(ForecastActivity.class);
                        }
                        break;
                    case R.id.settings:
                        if (!(CommonPresenter.this instanceof SettingsPresenter)) {
                            view.startActivity(SettingsActivity.class);
                        }
                        break;
                }

                return true;
            }
        };
    }

    void onPermissionResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean hasPermission = false;
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                view.hideGeolocationRationale();
                hasPermission = true;
                geolocate();
            }
        }

        if (!hasPermission) {
            view.showGeolocationPermissionRationale(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getPermissionUtils().requestGeolocationPermission(PERMISSION_REQUEST_CODE);
                }
            });
        }
    }

    protected abstract GoogleSignInClient getGoogleSignInClient();

    protected abstract PermissionUtils getPermissionUtils();

    protected abstract GoogleSignInAccount getGoogleSignInAccount();

    @Nullable
    protected abstract LocationUtils getLocationUtils();

    protected boolean needGeolocationonStartup() {
        return false;
    }

    protected void onLocationAvailable(Location location) {
    }
}
