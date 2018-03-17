package net.gahfy.chilindoweather.ui.settings;

import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.ui.common.CommonActivity;

public class SettingsActivity extends CommonActivity<SettingsPresenter> implements SettingsView {
    @NonNull
    @Override
    protected SettingsPresenter instantiatePresenter() {
        return new SettingsPresenter(this);
    }
}