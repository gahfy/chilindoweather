package net.gahfy.chilindoweather.injection.module;

import android.content.Context;
import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.utils.MockInstance;
import net.gahfy.chilindoweather.utils.PreferencesUtils;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ContextModule.class})
// Safe because it is used in app code but not in tests
@SuppressWarnings("UnusedDeclaration")
public class PreferencesModule {
    @NonNull
    private static final PreferencesModule instance = new PreferencesModule();

    private PreferencesModule() {
    }

    @NonNull
    // Safe because it is used in app code but not in tests
    @SuppressWarnings("UnusedDeclaration")
    public static PreferencesModule getInstance() {
        return instance;
    }

    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static PreferencesUtils providePreferencesUtils(@NonNull Context context) {
        PreferencesUtils preferencesUtils = Mockito.mock(PreferencesUtils.class);
        Mockito.when(preferencesUtils.getTemperatureIndex()).thenReturn(MockInstance.preferredTemperatureIndex);
        Mockito.when(preferencesUtils.getWindSpeedIndex()).thenReturn(MockInstance.preferredSpeedIndex);
        return preferencesUtils;
    }
}
