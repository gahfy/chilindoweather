package net.gahfy.chilindoweather.injection.module;


import android.content.Context;
import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.ChilindoWeatherApplication;
import net.gahfy.chilindoweather.ui.base.BaseView;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public final class ContextModule {
    @NonNull
    private static final ContextModule instance = new ContextModule();

    private ContextModule() {
    }

    @NonNull
    public static ContextModule getInstance() {
        return instance;
    }

    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    public static Context provideContext(@NonNull final BaseView view) {
        ChilindoWeatherApplication application = Mockito.mock(ChilindoWeatherApplication.class);
        Mockito.when(application.getApplicationContext()).thenReturn(application);
        return application;
    }

    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    public static ChilindoWeatherApplication provideApplication(@NonNull final Context context) {
        return (ChilindoWeatherApplication) context.getApplicationContext();
    }
}
