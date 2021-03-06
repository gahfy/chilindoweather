package net.gahfy.chilindoweather.injection.module;

import android.content.Context;
import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.utils.PermissionUtils;

import dagger.Module;
import dagger.Provides;

/**
 * Module which provides all required dependencies about permissions.
 */
@Module(includes = {ContextModule.class})
public class PermissionModule {
    /**
     * Instance of PermissionModule (as it is a Singleton)
     */
    @NonNull
    private static final PermissionModule instance = new PermissionModule();

    /**
     * Instantiates a new PermissionModule. It is here just to make it private and use
     * PermissionModule only as a Singleton.
     */
    private PermissionModule() {
    }

    /**
     * Returns the instance of PermissionModule.
     *
     * @return the instance of PermissionModule
     */
    @NonNull
    public static PermissionModule getInstance() {
        return instance;
    }

    /**
     * Provides the permission utils.
     *
     * @param context Context in which the application is running
     * @return the permission utils
     */
    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static PermissionUtils providePermissionUtils(@NonNull Context context) {
        return new PermissionUtils(context);
    }
}
