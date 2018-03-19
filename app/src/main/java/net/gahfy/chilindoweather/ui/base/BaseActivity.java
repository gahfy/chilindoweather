package net.gahfy.chilindoweather.ui.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.gahfy.chilindoweather.ChilindoWeatherApplication;
import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.utils.ChilindoWeatherContextWrapper;

import java.util.Locale;

/**
 * Activity all Activity classes must extend. It provides required methods and presenter
 * instantiation and calls.
 *
 * @param <P> the type of presenter the Activity is based on
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    /**
     * The presenter associated to this Activity
     */
    @NonNull
    // Safe as it is initialized in onCreate()
    @SuppressWarnings("NullableProblems")
    protected P presenter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ChilindoWeatherContextWrapper.wrap(newBase, newBase.getString(R.string.language)));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ChilindoWeatherApplication) getApplicationContext()).addActivity(this);
        presenter = instantiatePresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ChilindoWeatherApplication) getApplicationContext()).removeActivity(this);
    }

    @SuppressWarnings("deprecation")
    public void setSystemLocaleLegacy(Configuration config, Locale locale) {
        config.locale = locale;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void setSystemLocale(Configuration config, Locale locale) {
        config.setLocale(locale);
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    @NonNull
    protected abstract P instantiatePresenter();

    @NonNull
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startActivity(Class<? extends BaseActivity> activityClass) {
        ((ChilindoWeatherApplication) getApplicationContext()).startActivity(this, activityClass);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
