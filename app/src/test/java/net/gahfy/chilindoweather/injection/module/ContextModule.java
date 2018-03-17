package net.gahfy.chilindoweather.injection.module;


import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.base.BaseView;

import org.junit.Test;
import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public final class ContextModule {
    @NonNull
    private static final ContextModule instance = new ContextModule();

    public static ContextModule getInstance(){
        return instance;
    }

    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static Context provideContext(@NonNull BaseView view) {
        Application application = Mockito.mock(Application.class);
        Mockito.when(application.getApplicationContext()).thenReturn(application);
        return application;
    }

    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static Application provideApplication(@NonNull Context context) {
        return (Application) context.getApplicationContext();
    }
}
