package net.gahfy.chilindoweather.ui.common;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public abstract class CommonActivity<P extends CommonPresenter> extends BaseActivity<P> implements CommonView {
    private boolean destroyed = true;
    private DrawerLayout drawerLayout;
    private View navigationHeader;
    private Menu navigationMenu;
    private Snackbar activitySnackbar;
    private Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
    public void onStart() {
        super.onStart();
        if (destroyed) {
            destroyed = false;
            presenter.onViewCreated(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState = presenter.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResumeView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onViewDestroyed();
        destroyed = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        presenter.onPermissionResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProfileInfo(Uri imageUrl, String userName, String userEmail) {
        RequestOptions requestOptions = RequestOptions.circleCropTransform()
                .placeholder(R.drawable.ic_account_placeholder)
                .error(R.drawable.ic_account_placeholder)
                .fallback(R.drawable.ic_account_placeholder);
        Glide.with(this).load(imageUrl).apply(requestOptions).into((ImageView) navigationHeader.findViewById(R.id.profilePicture));
        ((TextView) navigationHeader.findViewById(R.id.user_name)).setText(userName);
        ((TextView) navigationHeader.findViewById(R.id.user_email)).setText(userEmail);
    }

    @Override
    public void setMenuVisibility(int resId, boolean visibility) {
        navigationMenu.findItem(resId).setVisible(visibility);
    }

    @Override
    public void hideGeolocationRationale() {
        hideSnackbar();
    }

    @Override
    public Void showGeolocationPermissionRationale(View.OnClickListener retryClickListener) {
        activitySnackbar = Snackbar.make(findViewById(android.R.id.content), R.string.geolocation_permission_rationale, Snackbar.LENGTH_INDEFINITE);
        activitySnackbar.setAction(R.string.geolocation_permission_grant, retryClickListener);
        activitySnackbar.show();
        return null;
    }

    @Override
    public void removeUserInfo() {
        ((ImageView) navigationHeader.findViewById(R.id.profilePicture)).setImageDrawable(null);
        ((TextView) navigationHeader.findViewById(R.id.user_name)).setText("");
        ((TextView) navigationHeader.findViewById(R.id.user_email)).setText("");
    }

    @Override
    public void showNoGeolocationAvailableError(View.OnClickListener settingsClickListener) {
        activitySnackbar = Snackbar.make(findViewById(android.R.id.content), R.string.location_provider_disabled_message, Snackbar.LENGTH_INDEFINITE);
        activitySnackbar.setAction(R.string.location_provider_disabled_settings, settingsClickListener);
        activitySnackbar.show();
    }

    @Override
    public void showGoogleSignInError(DialogInterface.OnClickListener retryClickListener) {
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
    public void closeDrawers() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void checkMenu(int resId) {
        for (int i = 0; i < navigationMenu.size(); i++) {
            MenuItem currentItem = navigationMenu.getItem(i);
            currentItem.setChecked(currentItem.getItemId() == resId);
        }
    }

    private void hideSnackbar() {
        if (activitySnackbar != null) {
            activitySnackbar.dismiss();
            activitySnackbar = null;
        }
    }
}
