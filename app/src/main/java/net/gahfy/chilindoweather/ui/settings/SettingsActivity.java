package net.gahfy.chilindoweather.ui.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.ui.common.CommonActivity;

// Safe as this issue is due to AppCompatActivity
@java.lang.SuppressWarnings("squid:MaximumInheritanceDepth")
public final class SettingsActivity extends CommonActivity<SettingsPresenter> implements SettingsView {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_settings, (ViewGroup) findViewById(R.id.content_container), true);

        findViewById(R.id.settings_temperature_block).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onTemperatureClick();
            }
        });

        findViewById(R.id.settings_wind_speed_block).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onWindSpeedClick();
            }
        });
    }

    @Override
    public void showTemperatureUnit(@StringRes int temperatureUnit) {
        ((TextView) findViewById(R.id.settings_temperature_value)).setText(temperatureUnit);
    }

    @Override
    public void showWindSpeedUnit(@StringRes int windSpeedUnit) {
        ((TextView) findViewById(R.id.settings_wind_speed_value)).setText(windSpeedUnit);
    }

    @NonNull
    @Override
    protected SettingsPresenter instantiatePresenter() {
        return new SettingsPresenter(this);
    }

    @Override
    public void showTemperatureDialog(DialogInterface.OnClickListener clickListener, int selectedIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(SettingsActivity.this, R.style.AlertDialogTheme));
        builder.setTitle(R.string.settings_temperature_unit_label);
        builder.setSingleChoiceItems(R.array.settings_temperature_units, selectedIndex, clickListener);
        builder.create().show();
    }

    @Override
    public void showWindSpeedDialog(DialogInterface.OnClickListener clickListener, int selectedIndex) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(SettingsActivity.this, R.style.AlertDialogTheme));
        builder.setTitle(R.string.settings_wind_speed_label);
        builder.setSingleChoiceItems(R.array.settings_wind_speed_unit, selectedIndex, clickListener);
        builder.create().show();
    }
}