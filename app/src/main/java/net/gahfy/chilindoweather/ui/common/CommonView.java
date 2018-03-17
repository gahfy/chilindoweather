package net.gahfy.chilindoweather.ui.common;

import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;

import net.gahfy.chilindoweather.base.BaseView;

public interface CommonView extends BaseView {
    void showProfileInfo(Uri imageUrl, String userName, String userEmail);

    void setMenuVisibility(int resId, boolean visibility);

    void hideGeolocationRationale();

    void showGeolocationPermissionRationale(View.OnClickListener retryClickListener);

    void removeUserInfo();

    void showNoGeolocationAvailableError(View.OnClickListener settingsClickListener);

    void showGoogleSignInError(DialogInterface.OnClickListener retryClickListener);

    void closeDrawers();

    void checkMenu(int menuResId);
}
