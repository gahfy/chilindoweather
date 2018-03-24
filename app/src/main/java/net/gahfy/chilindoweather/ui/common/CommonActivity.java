package net.gahfy.chilindoweather.ui.common;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.ui.base.BaseActivity;

/**
 * Activity all Activity related to weather must extend. It provides required methods and presenter
 * calls.
 *
 * @param <P> the type of CommonPresenter the Activity is based on
 */
// Safe as this issue is due to AppCompatActivity
@java.lang.SuppressWarnings("squid:MaximumInheritanceDepth")
public abstract class CommonActivity<P extends CommonPresenter> extends BaseActivity<P> implements CommonView {
    private boolean destroyed = true;
    private DrawerLayout drawerLayout;
    private View navigationHeader;
    private Menu navigationMenu;
    private Snackbar activitySnackbar;
    private Bundle savedInstanceState;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);
        this.savedInstanceState = savedInstanceState;

        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(presenter.getOnNavigationItemSelectedListener());
        navigationMenu = navigationView.getMenu();
        navigationHeader = navigationView.getHeaderView(0);

        RequestOptions options = new RequestOptions().centerCrop();

        Glide.with(getContext())
                .load(ContextCompat.getDrawable(this, R.drawable.bkg_drawer_meanu_header))
                .apply(options)
                .into((ImageView) navigationHeader.findViewById(R.id.background));
    }

    @Override
    public final void onStart() {
        super.onStart();
        if (destroyed) {
            destroyed = false;
            presenter.onViewCreated(savedInstanceState);
        }
    }

    @Override
    public final void onSaveInstanceState(@NonNull final Bundle outState) {
        presenter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public final void onResume() {
        super.onResume();
        presenter.onResumeView();
    }

    @Override
    public final void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroyed();
        destroyed = true;
    }

    @Override
    public final void onActivityResult(final int requestCode, final int resultCode, @NonNull final Intent data) {
        presenter.onActivityResult(requestCode, data);
    }

    @Override
    public final void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        presenter.onPermissionResult(requestCode, grantResults);
    }

    @Override
    public final boolean onOptionsItemSelected(@NonNull final MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public final void showProfileInfo(@Nullable final Uri imageUrl, @Nullable final String userName, @Nullable final String userEmail) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform()
                .placeholder(R.drawable.ic_account_placeholder)
                .error(R.drawable.ic_account_placeholder)
                .fallback(R.drawable.ic_account_placeholder);
        Glide.with(this).load(imageUrl).apply(requestOptions).into((ImageView) navigationHeader.findViewById(R.id.profilePicture));
        ((TextView) navigationHeader.findViewById(R.id.user_name)).setText(userName != null ? userName : "");
        ((TextView) navigationHeader.findViewById(R.id.user_email)).setText(userEmail != null ? userEmail : "");
    }

    @Override
    public final void setMenuItemVisibility(final int resId, final boolean visibility) {
        navigationMenu.findItem(resId).setVisible(visibility);
    }

    @Override
    public final void hideGeolocationRationale() {
        hideSnackbar();
    }

    @Override
    public final void hideNoGeolocationAvailableError() {
        hideSnackbar();
    }

    @Override
    public final void showGeolocationPermissionRationale(@NonNull final View.OnClickListener retryClickListener) {
        activitySnackbar = Snackbar.make(findViewById(android.R.id.content), R.string.geolocation_permission_rationale, Snackbar.LENGTH_INDEFINITE);
        activitySnackbar.setAction(R.string.geolocation_permission_grant, retryClickListener);
        activitySnackbar.show();
    }

    @Override
    public final void showNetworkError(@NonNull final View.OnClickListener onClickListener) {
        activitySnackbar = Snackbar.make(findViewById(android.R.id.content), R.string.network_error_message, Snackbar.LENGTH_INDEFINITE);
        activitySnackbar.setAction(R.string.retry, onClickListener);
        activitySnackbar.show();
    }

    @Override
    public final void showContent() {
        findViewById(R.id.content_container).setVisibility(View.VISIBLE);
    }

    @Override
    public final void hideContent() {
        findViewById(R.id.content_container).setVisibility(View.GONE);
    }

    @Override
    public final void removeUserInfo() {
        ((ImageView) navigationHeader.findViewById(R.id.profilePicture)).setImageDrawable(null);
        ((TextView) navigationHeader.findViewById(R.id.user_name)).setText("");
        ((TextView) navigationHeader.findViewById(R.id.user_email)).setText("");
    }

    @Override
    public final void showNoGeolocationAvailableError(@NonNull final View.OnClickListener settingsClickListener) {
        activitySnackbar = Snackbar.make(findViewById(android.R.id.content), R.string.location_provider_disabled_message, Snackbar.LENGTH_INDEFINITE);
        activitySnackbar.setAction(R.string.location_provider_disabled_settings, settingsClickListener);
        activitySnackbar.show();
    }

    @Override
    public final void showGoogleSignInError(@NonNull final DialogInterface.OnClickListener retryClickListener) {
        final AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

        dlgAlert.setTitle(R.string.google_sign_in_error_title);
        dlgAlert.setMessage(R.string.google_sign_in_error_message);
        dlgAlert.setPositiveButton(R.string.google_sign_in_error_retry, retryClickListener);
        dlgAlert.setNegativeButton(R.string.google_sign_in_error_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }

    @Override
    public final void closeDrawers() {
        drawerLayout.closeDrawers();
    }

    public final void showLoading(@StringRes final int loadingMessageResId) {
        findViewById(R.id.loading_layout).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.loading_message)).setText(loadingMessageResId);
    }

    public final void hideLoading() {
        findViewById(R.id.loading_layout).setVisibility(View.GONE);
    }

    @Override
    public final void checkMenu(final int resId) {
        for (int i = 0; i < navigationMenu.size(); i++) {
            MenuItem currentItem = navigationMenu.getItem(i);
            currentItem.setChecked(currentItem.getItemId() == resId);
        }
    }

    /**
     * Hides the displayed SnackBar
     */
    private void hideSnackbar() {
        if (activitySnackbar != null) {
            activitySnackbar.dismiss();
            activitySnackbar = null;
        }
    }
}
