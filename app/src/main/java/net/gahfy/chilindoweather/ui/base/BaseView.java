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
    // Safe as we pass only views to Presenter, not activities
    @SuppressWarnings("EmptyMethod")
    // Safe as it is used by Activity
    void startActivityForResult(@SuppressWarnings("unused") @NonNull final Intent intent,
                                @SuppressWarnings("unused") final int requestCode);

    /**
     * Terminates the view
     */
    // Safe as we pass only views to Presenter, not activities
    @SuppressWarnings("EmptyMethod")
    void finish();
}
