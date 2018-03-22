package net.gahfy.chilindoweather.injection.module;

import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.ChilindoWeatherApplication;
import net.gahfy.chilindoweather.utils.location.LocationUtils;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Module which provides all required dependencies about location.
 */
@Module(includes = {ContextModule.class})
public class LocationModule {
    /**
     * Instance of LocationModule (as it is a Singleton)
     */
    @NonNull
    private static final LocationModule instance = new LocationModule();

    /**
     * Instantiates a new LocationModule. It is here just to make it private and use LocationModule
     * only as a Singleton.
     */
    private LocationModule() {
    }

    /**
     * Returns the instance of LocationModule.
     *
     * @return the instance of LocationModule
     */
    @NonNull
    public static LocationModule getInstance() {
        return instance;
    }

    /**
     * Provides the location utils.
     *
     * @param context Context in which the application is running
     * @return the location utils
     */
    @Provides
    @NonNull
    @Reusable
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static LocationUtils provideLocationUtils(@NonNull ChilindoWeatherApplication context) {
        return LocationUtils.getInstance(context);
    }
}