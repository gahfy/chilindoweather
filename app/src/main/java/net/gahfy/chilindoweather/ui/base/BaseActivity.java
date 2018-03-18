package net.gahfy.chilindoweather.ui.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import net.gahfy.chilindoweather.R;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration configuration = getResources().getConfiguration();
        configuration.setLayoutDirection(new Locale(getString(R.string.language)));
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        presenter = instantiatePresenter();
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
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
