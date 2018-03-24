package net.gahfy.chilindoweather.ui.common;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

import net.gahfy.chilindoweather.ui.base.BaseView;

/**
 * Common view for weather providing all required methods for weather related views.
 */
public interface CommonView extends BaseView {
    /**
     * Displays the user profile information.
     *
     * @param imageUrl  the Uri of the profile image of the user
     * @param userName  the username to display
     * @param userEmail the user email to display
     */
    void showProfileInfo(@Nullable final Uri imageUrl, @Nullable final String userName, @Nullable final String userEmail);

    /**
     * Sets the visibility of a menu item.
     *
     * @param resId      the resource identifier of the menu item on which to apply change of visibility.
     * @param visibility whether the item should be visible or not
     */
    void setMenuItemVisibility(final int resId, final boolean visibility);

    /**
     * Hides the geolocation rationale
     */
    void hideGeolocationRationale();

    /**
     * Shows the geolocation rationale
     * @param retryClickListener the click listener to handle user click on retry
     */
    void showGeolocationPermissionRationale(@NonNull final View.OnClickListener retryClickListener);

    /**
     * Remove the displayed user information
     */
    void removeUserInfo();

    /**
     * Hides the No Geolocation Available error
     */
    void hideNoGeolocationAvailableError();

    /**
     * Shows the No Geolocation Available error
     * @param settingsClickListener the click listener to handle user click on settings
     */
    void showNoGeolocationAvailableError(@NonNull final View.OnClickListener settingsClickListener);

    /**
     * Shows the Google Sign-In error
     * @param retryClickListener the click listener to handle user click on retry
     */
    void showGoogleSignInError(@NonNull final DialogInterface.OnClickListener retryClickListener);

    /**
     * Closes the navigation drawer
     */
    void closeDrawers();

    /**
     * Sets the specified menu as selected.
     * @param menuResId the resId of the menu to set as selected
     */
    void checkMenu(final int menuResId);

    /**
     * Shows the loading state.
     * @param loadingMessageResId the res id of the message to display below ProgressBar
     */
    void showLoading(@StringRes final int loadingMessageResId);

    /**
     * Hides loading state
     */
    void hideLoading();

    /**
     * Shows the network error.
     * @param onClickListener click listener to handle user click on retry
     */
    void showNetworkError(@NonNull final View.OnClickListener onClickListener);

    /**
     * Shows the main content of the view
     */
    void showContent();

    /**
     * Hides the main content of the view
     */
    void hideContent();
}
