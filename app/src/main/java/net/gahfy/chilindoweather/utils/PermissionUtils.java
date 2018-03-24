package net.gahfy.chilindoweather.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public final class PermissionUtils {
    private Activity activity;

    public PermissionUtils(@NonNull final Context context) {
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        } else {
            throw new IllegalArgumentException("Provided context must be an instance of Activity");
        }
    }

    public final boolean hasFineLocationPermission() {
        return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public final boolean shouldShowGeolocationRationale() {
        return shouldShowRationale(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    public final void requestGeolocationPermission(final int requestCode) {
        requestPermission(requestCode, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    private boolean hasPermission(@NonNull final String permission) {
        return ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    private boolean shouldShowRationale(@NonNull final String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
    }

    // Safe as this is a utils method
    @SuppressWarnings("unused")
    private void requestPermission(final int requestCode, @NonNull final String... permissions) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }
}
