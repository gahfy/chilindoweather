package net.gahfy.chilindoweather.ui.settings;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import net.gahfy.chilindoweather.ui.common.CommonPresenter;
import net.gahfy.chilindoweather.utils.location.LocationUtils;
import net.gahfy.chilindoweather.utils.permissions.PermissionUtils;

import javax.inject.Inject;

public class SettingsPresenter extends CommonPresenter<SettingsView> {
    @Inject
    @Nullable
    GoogleSignInAccount googleSignInAccount;

    @Inject
    PermissionUtils permissionUtils;

    @Inject
    GoogleSignInClient googleSignInClient;

    SettingsPresenter(@NonNull SettingsView view) {
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
        return null;
    }
}
