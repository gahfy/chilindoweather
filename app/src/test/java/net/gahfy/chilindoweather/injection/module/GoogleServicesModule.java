package net.gahfy.chilindoweather.injection.module;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import net.gahfy.chilindoweather.utils.MockInstance;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module(includes = {ContextModule.class})
public class GoogleServicesModule {
    @NonNull
    private static final GoogleServicesModule instance = new GoogleServicesModule();

    private GoogleServicesModule() {
    }

    @NonNull
    // Safe as it is used in app code
    @SuppressWarnings("unused")
    public static GoogleServicesModule getInstance() {
        return instance;
    }

    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static GoogleApiClient provideGoogleApiClient(GoogleSignInOptions googleSignInOptions, Application context) {
        return Mockito.mock(GoogleApiClient.class);
    }

    @Provides
    @NonNull
    // Safe as it is used in app code
    @SuppressWarnings("unused")
    static GoogleSignInClient provideGoogleSignInClient(GoogleSignInOptions googleSignInOptions, Application context) {
        return Mockito.mock(GoogleSignInClient.class);
    }

    @Provides
    @Nullable
    // Safe as it is used in app code
    @SuppressWarnings("unused")
    static GoogleSignInAccount provideGoogleSignInAccount(Application context) {
        return MockInstance.googleSignInAccount;
    }

    @Provides
    @NonNull
    @Reusable
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static GoogleSignInOptions provideGoogleSignInOptions() {
        return Mockito.mock(GoogleSignInOptions.class);
    }
}
