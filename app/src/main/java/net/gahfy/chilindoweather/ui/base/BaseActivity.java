package net.gahfy.chilindoweather.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import net.gahfy.chilindoweather.ChilindoWeatherApplication;
import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.utils.ChilindoWeatherContextWrapper;

/**
 * Activity all Activity classes must extend. It provides required methods and presenter
 * instantiation and calls.
 *
 * @param <P> the type of presenter the Activity is based on
 */
// Safe as this issue is due to AppCompatActivity
@java.lang.SuppressWarnings("squid:MaximumInheritanceDepth")
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    /**
     * The presenter associated to this Activity
     */
    @NonNull
    // Safe as it is initialized in onCreate()
    @SuppressWarnings("NullableProblems")
    protected P presenter;

    @Override
    protected final void attachBaseContext(@NonNull Context newBase) {
        super.attachBaseContext(ChilindoWeatherContextWrapper.wrap(newBase, newBase.getString(R.string.language)));
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ChilindoWeatherApplication) getApplicationContext()).addActivity(this);
        presenter = instantiatePresenter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((ChilindoWeatherApplication) getApplicationContext()).removeActivity(this);
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    @NonNull
    protected abstract P instantiatePresenter();

    @NonNull
    @Override
    public final Context getContext() {
        return this;
    }

    @Override
    public final void startActivity(@NonNull final Class<? extends BaseActivity> activityClass) {
        ((ChilindoWeatherApplication) getApplicationContext()).startActivity(this, activityClass);
    }

    @Override
    public final void setTitle(@StringRes final int titleResId, @NonNull final String... formatString) {
        super.setTitle(getString(titleResId, (Object[]) formatString));
    }
}
