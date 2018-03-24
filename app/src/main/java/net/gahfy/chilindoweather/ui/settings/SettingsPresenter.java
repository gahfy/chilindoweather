package net.gahfy.chilindoweather.ui.settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.ui.common.CommonPresenter;
import net.gahfy.chilindoweather.utils.LocationUtils;
import net.gahfy.chilindoweather.utils.PermissionUtils;
import net.gahfy.chilindoweather.utils.PreferencesUtils;

import javax.inject.Inject;

public final class SettingsPresenter extends CommonPresenter<SettingsView> {
    @Inject
    @Nullable
    // Safe as Injected members must be not private
    @SuppressWarnings("WeakerAccess")
    GoogleSignInAccount googleSignInAccount;

    @Inject
    // Safe as provide method is @NonNull
    // Safe as Injected members must be not private
    @SuppressWarnings({"NullableProblems", "WeakerAccess"})
    @NonNull
    PermissionUtils permissionUtils;

    @Inject
    // Safe as provide method is @NonNull
    // Safe as Injected members must be not private
    @SuppressWarnings({"NullableProblems", "WeakerAccess"})
    @NonNull
    GoogleSignInClient googleSignInClient;

    @Inject
    // Safe as provide method is @NonNull
    // Safe as Injected members must be not private
    @SuppressWarnings({"NullableProblems", "WeakerAccess"})
    @NonNull
    PreferencesUtils preferencesUtils;

    // Safe as non null members are injected by @NonNull providers
    @SuppressWarnings("squid:S2637")
    SettingsPresenter(@NonNull final SettingsView view) {
        super(view);
    }

    @Override
    public final void onViewCreated(@Nullable final Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        int temperatureUnit = getTemperatureUnitResId(preferencesUtils.getTemperatureIndex());
        int windSpeedUnit = getWindSpeedUnitResId(preferencesUtils.getWindSpeedIndex());
        view.showTemperatureUnit(temperatureUnit);
        view.showWindSpeedUnit(windSpeedUnit);
        view.setTitle(R.string.settings);
    }

    final void onTemperatureClick() {
        view.showTemperatureDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preferencesUtils.setTemperatureIndex(which);
                view.showTemperatureUnit(getTemperatureUnitResId(which));
                dialog.dismiss();
            }
        }, preferencesUtils.getTemperatureIndex());
    }

    final void onWindSpeedClick() {
        view.showWindSpeedDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preferencesUtils.setWindSpeedIndex(which);
                view.showWindSpeedUnit(getWindSpeedUnitResId(which));
                dialog.dismiss();
            }
        }, preferencesUtils.getWindSpeedIndex());
    }

    private int getTemperatureUnitResId(final int selectedIndex) {
        return selectedIndex == 0 ? R.string.settings_temperature_celsius : R.string.settings_temperature_fahrenheit;
    }

    private int getWindSpeedUnitResId(final int selectedIndex) {
        return selectedIndex == 0 ? R.string.settings_wind_speed_m : R.string.settings_wind_speed_mi;
    }

    @Override
    @NonNull
    protected final GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    @Override
    @Nullable
    protected final LocationUtils getLocationUtils() {
        return null;
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

}
