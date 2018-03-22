package net.gahfy.chilindoweather.injection.module;

import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.ChilindoWeatherApplication;
import net.gahfy.chilindoweather.utils.preferences.PreferencesUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides all required dependencies about preferences.
 */
@Module(includes = {ContextModule.class})
public class PreferencesModule {
    /**
     * Instance of PreferencesModule (as it is a Singleton)
     */
    @NonNull
    private static final PreferencesModule instance = new PreferencesModule();

    /**
     * Instantiates a new PreferencesModule. It is here just to make it private and use
     * PreferencesModule only as a Singleton.
     */
    private PreferencesModule() {
    }

    /**
     * Returns the instance of PreferencesModule.
     *
     * @return the instance of PreferencesModule
     */
    @NonNull
    public static PreferencesModule getInstance() {
        return instance;
    }

    /**
     * Provides the preference utils.
     *
     * @param context Context in which the application is running
     * @return the preference utils
     */
    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static PreferencesUtils providePreferencesUtils(@NonNull ChilindoWeatherApplication context) {
        return new PreferencesUtils(context);
    }
}
