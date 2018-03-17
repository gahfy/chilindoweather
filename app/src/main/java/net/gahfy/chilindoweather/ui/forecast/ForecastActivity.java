package net.gahfy.chilindoweather.ui.forecast;

import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.ui.common.CommonActivity;

public class ForecastActivity extends CommonActivity<ForecastPresenter> implements ForecastView {
    @NonNull
    @Override
    protected ForecastPresenter instantiatePresenter() {
        return new ForecastPresenter(this);
    }
}
