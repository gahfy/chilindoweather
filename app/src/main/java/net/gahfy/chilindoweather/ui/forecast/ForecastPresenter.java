package net.gahfy.chilindoweather.ui.forecast;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import net.gahfy.chilindoweather.ui.common.CommonPresenter;
import net.gahfy.chilindoweather.utils.location.LocationUtils;
import net.gahfy.chilindoweather.utils.permissions.PermissionUtils;

import javax.inject.Inject;

public class ForecastPresenter extends CommonPresenter<ForecastView> {
    @Inject
    @Nullable
    GoogleSignInAccount googleSignInAccount;

    @Inject
    GoogleSignInClient googleSignInClient;

    @Inject
    PermissionUtils permissionUtils;

    @Inject
    LocationUtils locationUtils;

    public ForecastPresenter(@NonNull ForecastView view) {
        super(view);
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

    }
}
