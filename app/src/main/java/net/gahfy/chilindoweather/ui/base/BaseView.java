package net.gahfy.chilindoweather.ui.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Base view any view must implement
 */
public interface BaseView {
    /**
     * Returns the context in which the application is running.
     *
     * @return the context in which the application is running
     */
    @NonNull
    Context getContext();


    void startActivity(Class<? extends BaseActivity> activityClass);

    void startActivityForResult(Intent intent, int requestCode);

    void finish();
}
