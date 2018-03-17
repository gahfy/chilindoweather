package net.gahfy.chilindoweather.injection.module;

import android.app.Application;
import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.utils.location.LocationUtils;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module(includes = {ContextModule.class})
public class LocationModule {
    @NonNull
    private static final LocationModule instance = new LocationModule();


    private LocationModule() {
    }

    @NonNull
    public static LocationModule getInstance() {
        return instance;
    }

    @Provides
    @NonNull
    @Reusable
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static LocationUtils provideLocationUtils(@NonNull Application context) {
        return LocationUtils.getInstance(context);
    }
}