package net.gahfy.chilindoweather.ui.common;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
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
import net.gahfy.chilindoweather.ui.weather.WeatherActivity;
import net.gahfy.chilindoweather.ui.weather.WeatherPresenter;
import net.gahfy.chilindoweather.utils.HandlerUtils;
import net.gahfy.chilindoweather.utils.LocationUtils;
import net.gahfy.chilindoweather.utils.Logger;
import net.gahfy.chilindoweather.utils.PermissionUtils;

/**
 * Common presenter that all presenter related to weather must implement.
 *
 * @param <V> the type of View the presenter is associated to.
 */
public abstract class CommonPresenter<V extends CommonView> extends BasePresenter<V> {
    /**
     * The request code of asking for geolocation permission
     */
    private static final int PERMISSION_REQUEST_CODE = 1;

    /**
     * The request code for result of Google Sign-In
     */
    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 2;

    /**
     * Click listener to handle click on GRANT on geolocation rationale message
     */
    @NonNull
    private final View.OnClickListener rationaleRetryClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            view.showGeolocationPermissionRationale(this);
            getPermissionUtils().requestGeolocationPermission(PERMISSION_REQUEST_CODE);
        }
    };

    /**
     * Location listener to handle location events
     */
    @NonNull
    private final LocationUtils.SingleLocationListener locationListener = new LocationUtils.SingleLocationListener() {
        private static final String TAG = "CommonPresenter.locationListener";

        @Override
        public final void onLocationFound(@NonNull final Location location) {
            Logger.d(TAG, "Location found: " + location.getLatitude() + "," + location.getLongitude());
            onLocationAvailable(location);
        }

        @Override
        public final void onGeolocationStarted() {
            view.hideNoGeolocationAvailableError();
        }

        @Override
        public final void onProviderNotAvailableError() {
            view.showNoGeolocationAvailableError(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProviderNotAvailableError();
                    view.startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
                }
            });
        }

        @Override
        public final void onPermissionError() {
            manageGeolocationPermission();
        }
    };

    /**
     * Instantiates a new BasePresenter and inject required dependencies.
     *
     * @param view the view associated to the presenter to set
     */
    public CommonPresenter(@NonNull final V view) {
        super(view);
    }

    public void onViewCreated(@Nullable final Bundle savedInstanceState) {
    }

    public void saveInstanceState(@NonNull final Bundle outState){
    }

    /**
     * Method that must be called when the view is resumed
     */
    protected void onResumeView() {
        inject();
        checkRelevantMenu();
        presentAccount(getGoogleSignInAccount());
        if (needGeolocationonStartup()) {
            manageGeolocationPermission();
        }
    }

    /**
     * Method that must be called when the view is destroyed
     */
    protected void onViewDestroyed() {
        getLocationUtils().removeSingleLocationListener(locationListener);
    }

    /**
     * Handles google Sign-In result
     *
     * @param requestCode the request code (to check if it's a Google Sign-In result)
     * @param data        the data sent by Google Sign-In
     */
    final void onActivityResult(final int requestCode, @NonNull final Intent data) {
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            GoogleSignInResult result = getSignInResultFromIntent(data);
            onGoogleSignInResult(result);
        }
    }

    /**
     * Method that must be called when a result from permission is retrieved.
     *
     * @param requestCode  the request code
     * @param grantResults the result of permission request
     */
    final void onPermissionResult(final int requestCode, @NonNull final int[] grantResults) {
        boolean hasPermission = false;
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            view.hideGeolocationRationale();
            hasPermission = true;
            geolocate();
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

    /**
     * Performs actions regarding the location permission (geolocate, ask for permission or show
     * rationale)
     */
    private void manageGeolocationPermission() {
        if (getPermissionUtils().hasFineLocationPermission()) {
            view.hideGeolocationRationale();
            view.showLoading(R.string.loading_location);
            geolocate();
        } else {
            if (getPermissionUtils().shouldShowGeolocationRationale()) {
                view.showLoading(R.string.loading_permission);
                view.showGeolocationPermissionRationale(rationaleRetryClickListener);
            } else {
                view.showLoading(R.string.loading_permission);
                getPermissionUtils().requestGeolocationPermission(PERMISSION_REQUEST_CODE);
            }
        }
    }

    /**
     * Returns a GoogleSignInResult from data in the specified Intent.
     *
     * @param data the data sent by Google Sign-In
     * @return a GoogleSignInResult from data in the specified Internt
     */
    private GoogleSignInResult getSignInResultFromIntent(@NonNull final Intent data) {
        return Auth.GoogleSignInApi.getSignInResultFromIntent(data);
    }

    /**
     * Performs Google Sign-In
     */
    private void onSignInClick() {
        checkRelevantMenu();
        Intent signInIntent = getGoogleSignInClient().getSignInIntent();
        view.startActivityForResult(signInIntent, GOOGLE_SIGN_IN_REQUEST_CODE);
    }

    /**
     * Performs Google Sign-Out
     */
    private void onSignOutClick() {
        checkRelevantMenu();
        getGoogleSignInClient().signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                view.removeUserInfo();
                view.setMenuItemVisibility(R.id.sign_in, true);
                view.setMenuItemVisibility(R.id.sign_out, false);
            }
        });
    }

    /**
     * Called when a GoogleSignInResult is retrieved.
     *
     * @param result the GoogleSignInResult that has been retrieved
     */
    private void onGoogleSignInResult(@NonNull final GoogleSignInResult result) {
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

    /**
     * Starts Geolocation
     */
    // Safe as Geolocate is only called when permission is granted
    @SuppressLint("MissingPermission")
    private void geolocate() {
        view.showLoading(R.string.loading_location);
        getLocationUtils().addSingleLocationListener(locationListener);
    }

    /**
     * Presents the Google account in the view.
     *
     * @param account the Google account to present
     */
    private void presentAccount(@Nullable final GoogleSignInAccount account) {
        if (account != null) {
            view.setMenuItemVisibility(R.id.sign_in, false);
            view.setMenuItemVisibility(R.id.sign_out, true);
            view.showProfileInfo(account.getPhotoUrl(), account.getDisplayName(), account.getEmail());
        } else {
            view.setMenuItemVisibility(R.id.sign_in, true);
            view.setMenuItemVisibility(R.id.sign_out, false);
            view.removeUserInfo();
        }
    }

    /**
     * Shows Sign-In or Logout regarding status (logged or not) of the user
     */
    private void checkRelevantMenu() {
        if (this instanceof WeatherPresenter) {
            view.checkMenu(R.id.current_weather);
        } else if (this instanceof SettingsPresenter) {
            view.checkMenu(R.id.settings);
        } else if (this instanceof ForecastPresenter) {
            view.checkMenu(R.id.weather_forecast);
        }
    }

    /**
     * Returns the NavigationItemClickListener to handle when a user clicks on an item of the
     * NavigationDrawer.
     * @return the NavigationItemClickListener
     */
    // Safe as it in use for unit tests
    @SuppressWarnings("WeakerAccess")
    @NonNull
    public final NavigationView.OnNavigationItemSelectedListener getOnNavigationItemSelectedListener() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                view.closeDrawers();

                HandlerUtils.postDelayed(new MenuItemRunnable(menuItem) {
                    @Override
                    public void run() {
                        switch (this.menuItem.getItemId()) {
                            case R.id.sign_in:
                                onSignInClick();
                                break;
                            case R.id.sign_out:
                                onSignOutClick();
                                break;
                            case R.id.current_weather:
                                view.startActivity(WeatherActivity.class);
                                break;
                            case R.id.weather_forecast:
                                view.startActivity(ForecastActivity.class);
                                break;
                            case R.id.settings:
                                view.startActivity(SettingsActivity.class);
                                break;
                            default:
                                break;
                        }
                    }
                }, 250);


                if (menuItem.getItemId() != R.id.settings && CommonPresenter.this instanceof SettingsPresenter) {
                    view.finish();
                }
                return true;
            }
        };
    }

    /**
     * Returns the GoogleSignInClient injected in final presenters.
     * @return the GoogleSignInClient injected in final presenters
     */
    @NonNull
    protected abstract GoogleSignInClient getGoogleSignInClient();

    /**
     * Returns the PermissionUtils injected in final presenters.
     * @return the PermissionUtils injected in final presenters
     */
    @NonNull
    protected abstract PermissionUtils getPermissionUtils();

    /**
     * Returns the GoogleSignInAccount injected in final presenters.
     * @return the GoogleSignInAccount injected in final presenters
     */
    @Nullable
    protected abstract GoogleSignInAccount getGoogleSignInAccount();

    /**
     * Returns the LocationUtils injected in final presenters.
     * @return the LocationUtils injected in final presenters
     */
    @NonNull
    protected abstract LocationUtils getLocationUtils();

    /**
     * Returns whether the view needs geolocation or not
     * @return whether the view needs geolocation or not
     */
    protected boolean needGeolocationonStartup() {
        return false;
    }

    /**
     * Called when location is available.
     *
     * @param location the retrieved location.
     */
    protected void onLocationAvailable(@NonNull final Location location) {
    }

    /**
     * MenuItemRunnable to avoid final in things that are Asynchronous
     */
    private abstract static class MenuItemRunnable implements Runnable {
        /**
         * MenuItem of the Runnable
         */
        @NonNull
        final MenuItem menuItem;

        /**
         * Instantiates a new MenuItemRunnable
         *
         * @param menuItem the menuItem to keep in the runnable
         */
        MenuItemRunnable(@NonNull final MenuItem menuItem) {
            super();
            this.menuItem = menuItem;
        }
    }
}
