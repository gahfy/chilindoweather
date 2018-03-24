package net.gahfy.chilindoweather.injection.module;

import android.content.Context;
import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.ChilindoWeatherApplication;
import net.gahfy.chilindoweather.ui.base.BaseView;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides all required dependencies about Context
 */
@Module
public final class ContextModule {
    /**
     * Instance of ContextModule (as it is a Singleton)
     */
    @NonNull
    private static final ContextModule instance = new ContextModule();

    /**
     * Instantiates a new ContextModule. It is here just to make it private and use ContextModule
     * only as a Singleton.
     */
    private ContextModule() {
    }

    /**
     * Returns the instance of ContextModule.
     *
     * @return the instance of ContextModule
     */
    @NonNull
    public static ContextModule getInstance() {
        return instance;
    }

    /**
     * Provides the Context in which the application is running.
     *
     * @param view the BaseView used to provide the Context
     * @return the Context in which the application is running
     */
    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static Context provideContext(@NonNull final BaseView view) {
        return view.getContext();
    }

    /**
     * Provides the currently running application.
     *
     * @param context the Context in which the application is running
     * @return the currently running application
     */
    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static ChilindoWeatherApplication provideApplication(@NonNull final Context context) {
        return (ChilindoWeatherApplication) context.getApplicationContext();
    }
}
