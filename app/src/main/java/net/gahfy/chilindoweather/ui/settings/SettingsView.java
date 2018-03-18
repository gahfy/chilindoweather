package net.gahfy.chilindoweather.ui.settings;

import android.content.DialogInterface;
import android.support.annotation.StringRes;

import net.gahfy.chilindoweather.ui.common.CommonView;

public interface SettingsView extends CommonView {
    void showTemperatureDialog(DialogInterface.OnClickListener clickListener, int selectedIndex);

    void showWindSpeedDialog(DialogInterface.OnClickListener clickListener, int selectedIndex);

    void showTemperatureUnit(@StringRes int temperatureUnit);

    void showWindSpeedUnit(@StringRes int windSpeedUnit);
}
