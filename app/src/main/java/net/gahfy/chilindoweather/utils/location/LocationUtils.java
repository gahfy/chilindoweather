package net.gahfy.chilindoweather.utils.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import net.gahfy.chilindoweather.utils.permissions.PermissionUtils;

import java.util.ArrayList;
import java.util.Date;

public class LocationUtils {
    private static final long RELOCATION_DELAY = 3600000;

    private static LocationUtils ourInstance;

    @Nullable
    private final LocationManager locationManager;

    @NonNull
    private final PermissionUtils permissionUtils;

    private boolean isGeolocating = false;

    private Location location = null;
    private long lastLocationTimestamp = 0;
    private boolean geolocationSent = false;

    private ArrayList<SingleLocationListener> singleLocationListeners = new ArrayList<>();

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            if (locationManager != null) {
                locationManager.removeUpdates(locationListener);
            }
            isGeolocating = false;
            if (!geolocationSent) {
                geolocationSent = true;
                LocationUtils.this.location = location;
                lastLocationTimestamp = new Date().getTime();
                for (SingleLocationListener singleLocationListener : singleLocationListeners) {
                    singleLocationListener.onLocationFound(location);
                }
            }
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    private LocationUtils(Context context) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        permissionUtils = new PermissionUtils(context);
    }

    public static LocationUtils getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new LocationUtils(context);
        }
        return ourInstance;
    }

    // Safe as check is made by PermissionUtils
    @SuppressLint("MissingPermission")
    private void getLocation() {
        if (permissionUtils.hasFineLocationPermission()) {
            if (locationManager != null && (
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                            locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)
            )) {
                isGeolocating = true;
                for (SingleLocationListener singleLocationListener : singleLocationListeners) {
                    singleLocationListener.onGeolocationStarted();
                }
                if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                }
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
                if (locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER)) {
                    locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
                }
            } else {
                for (SingleLocationListener singleLocationListener : singleLocationListeners) {
                    singleLocationListener.onProviderNotAvailableError();
                }
            }
        } else {
            for (SingleLocationListener singleLocationListener : singleLocationListeners) {
                singleLocationListener.onPermissionError();
            }
        }
    }

    public boolean hasLocationAvailable() {
        return location != null;
    }

    public Void addSingleLocationListener(SingleLocationListener singleLocationListener) {
        singleLocationListeners.add(singleLocationListener);
        if (location == null || new Date().getTime() - RELOCATION_DELAY > lastLocationTimestamp) {
            location = null;
            geolocationSent = false;
            if (!isGeolocating) {
                getLocation();
            }
        } else {
            singleLocationListener.onLocationFound(location);
        }
        return null;
    }

    public void removeSingleLocationListener(SingleLocationListener singleLocationListener) {
        singleLocationListeners.remove(singleLocationListener);
    }

    public interface SingleLocationListener {
        void onGeolocationStarted();

        void onLocationFound(Location location);

        void onProviderNotAvailableError();

        void onPermissionError();
    }
}
