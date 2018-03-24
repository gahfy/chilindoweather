package net.gahfy.chilindoweather.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;

public class LocationUtils {
    private static final long RELOCATION_DELAY = 3600000;

    private static LocationUtils ourInstance;

    @Nullable
    private final LocationManager locationManager;

    @NonNull
    private final PermissionUtils permissionUtils;
    private final ArrayList<SingleLocationListener> singleLocationListeners = new ArrayList<>();
    private boolean isGeolocating = false;
    private Location location = null;
    private long lastLocationTimestamp = 0;
    private boolean geolocationSent = false;
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
            // We do not handle this for now
        }

        public void onProviderEnabled(String provider) {
            // We do not handle this for now
        }

        public void onProviderDisabled(String provider) {
            // We do not handle this for now
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
                requestLocationUpdatesIfAvailable(LocationManager.NETWORK_PROVIDER);
                requestLocationUpdatesIfAvailable(LocationManager.GPS_PROVIDER);
                requestLocationUpdatesIfAvailable(LocationManager.PASSIVE_PROVIDER);
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

    // Safe as check is made by PermissionUtils
    @SuppressLint("MissingPermission")
    private void requestLocationUpdatesIfAvailable(String provider) {
        if (locationManager != null && locationManager.isProviderEnabled(provider)) {
            locationManager.requestLocationUpdates(provider, 0, 0, locationListener);
        }
    }

    public boolean hasLocationAvailable() {
        return location != null;
    }

    public void addSingleLocationListener(SingleLocationListener singleLocationListener) {
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
