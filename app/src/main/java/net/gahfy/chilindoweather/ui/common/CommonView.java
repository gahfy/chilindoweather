package net.gahfy.chilindoweather.ui.common;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.annotation.StringRes;
import android.view.View;

import net.gahfy.chilindoweather.ui.base.BaseView;

public interface CommonView extends BaseView {
    void showProfileInfo(Uri imageUrl, String userName, String userEmail);

    void setMenuVisibility(int resId, boolean visibility);

    void hideGeolocationRationale();

    Void showGeolocationPermissionRationale(View.OnClickListener retryClickListener);

    void removeUserInfo();

    void hideNoGeolocationAvailableError();

    void showNoGeolocationAvailableError(View.OnClickListener settingsClickListener);

    void showGoogleSignInError(DialogInterface.OnClickListener retryClickListener);

    void closeDrawers();

    void checkMenu(int menuResId);

    void showLoading(@StringRes int loadingMessageResId);

    void hideLoading();

    void showNetworkError(View.OnClickListener onClickListener);

    void showContent();

    void hideContent();
}
