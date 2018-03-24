package net.gahfy.chilindoweather.ui.common;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;

import net.gahfy.chilindoweather.ui.base.BaseView;

public interface CommonView extends BaseView {
    void showProfileInfo(@Nullable final Uri imageUrl, @Nullable final String userName, @Nullable final String userEmail);

    void setMenuVisibility(final int resId, final boolean visibility);

    void hideGeolocationRationale();

    void showGeolocationPermissionRationale(@NonNull final View.OnClickListener retryClickListener);

    void removeUserInfo();

    void hideNoGeolocationAvailableError();

    void showNoGeolocationAvailableError(@NonNull final View.OnClickListener settingsClickListener);

    void showGoogleSignInError(@NonNull final DialogInterface.OnClickListener retryClickListener);

    void closeDrawers();

    void checkMenu(final int menuResId);

    void showLoading(@StringRes final int loadingMessageResId);

    void hideLoading();

    void showNetworkError(@NonNull final View.OnClickListener onClickListener);

    void showContent();

    void hideContent();
}
