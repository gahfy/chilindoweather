package net.gahfy.chilindoweather.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionUtils {
    private Context context;

    public PermissionUtils(Context context) {
        this.context = context;
    }

    public boolean hasFineLocationPermission() {
        return hasPermission(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public boolean shouldShowGeolocationRationale() {
        return shouldShowRationale(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    public void requestGeolocationPermission(int requestCode) {
        requestPermission(requestCode, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private boolean hasPermission(String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private boolean shouldShowRationale(String permission) {
        if (context instanceof Activity) {
            return ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    permission);
        } else {
            throw new RuntimeException("Provided context must be an instance of Activity");
        }
    }

    private void requestPermission(int requestCode, String... permissions) {
        if (context instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) context,
                    permissions,
                    requestCode);
        } else {
            throw new RuntimeException("Provided context must be an instance of Activity");
        }
    }
}
