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

public class SettingsPresenter extends CommonPresenter<SettingsView> {
    @Inject
    @Nullable
    GoogleSignInAccount googleSignInAccount;

    @Inject
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    @NonNull
    PermissionUtils permissionUtils;

    @Inject
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    @NonNull
    GoogleSignInClient googleSignInClient;

    @Inject
    // Safe as provide method is @NonNull
    @SuppressWarnings("NullableProblems")
    @NonNull
    PreferencesUtils preferencesUtils;

    @SuppressWarnings("squid:S2637")
    public SettingsPresenter(@NonNull SettingsView view) {
        super(view);
    }

    public void onViewCreated(Bundle savedInstanceState) {
        super.onViewCreated(savedInstanceState);
        int temperatureUnit = getTemperatureUnitResId(preferencesUtils.getTemperatureIndex());
        int windSpeedUnit = getWindSpeedUnitResId(preferencesUtils.getWindSpeedIndex());
        view.showTemperatureUnit(temperatureUnit);
        view.showWindSpeedUnit(windSpeedUnit);
        view.setTitle(R.string.settings);
    }

    void onTemperatureClick() {
        view.showTemperatureDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preferencesUtils.setTemperatureIndex(which);
                view.showTemperatureUnit(getTemperatureUnitResId(which));
                dialog.dismiss();
            }
        }, preferencesUtils.getTemperatureIndex());
    }

    void onWindSpeedClick() {
        view.showWindSpeedDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                preferencesUtils.setWindSpeedIndex(which);
                view.showWindSpeedUnit(getWindSpeedUnitResId(which));
                dialog.dismiss();
            }
        }, preferencesUtils.getWindSpeedIndex());
    }

    private int getTemperatureUnitResId(int selectedIndex) {
        return selectedIndex == 0 ? R.string.settings_temperature_celsius : R.string.settings_temperature_fahrenheit;
    }

    private int getWindSpeedUnitResId(int selectedIndex) {
        return selectedIndex == 0 ? R.string.settings_wind_speed_m : R.string.settings_wind_speed_mi;
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
