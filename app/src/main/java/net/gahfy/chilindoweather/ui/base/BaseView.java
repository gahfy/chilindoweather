package net.gahfy.chilindoweather.ui.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

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

    /**
     * Sets the title of the view.
     *
     * @param titleResId   The resource identifier of the format title
     * @param formatString The arguments required to format the title
     */
    void setTitle(@StringRes final int titleResId, @NonNull final String... formatString);

    /**
     * Sets the title of the view.
     *
     * @param title title of the view
     */
    void setTitle(@NonNull final CharSequence title);

    /**
     * Starts an Activity.
     *
     * @param activityClass the Activity to start
     */
    void startActivity(@NonNull final Class<? extends BaseActivity> activityClass);

    /**
     * Starts an Activity for result.
     *
     * @param intent      the Intent describing the Activity to start
     * @param requestCode the request code to return when giving the result back
     */
    void startActivityForResult(@NonNull final Intent intent, final int requestCode);

    /**
     * Terminates the view
     */
    void finish();
}
