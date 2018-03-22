package net.gahfy.chilindoweather.injection.module;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

import net.gahfy.chilindoweather.ChilindoWeatherApplication;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

/**
 * Module which provides all required dependencies about Google Services Dependencies
 */
@Module(includes = {ContextModule.class})
public class GoogleServicesModule {
    /**
     * Instance of GoogleServicesModule (as it is a Singleton)
     */
    @NonNull
    private static final GoogleServicesModule instance = new GoogleServicesModule();

    /**
     * Instantiates a new GoogleServicesModule. It is here just to make it private and use
     * GoogleServicesModule only as a Singleton.
     */
    private GoogleServicesModule() {
    }

    /**
     * Returns the instance of GoogleServicesModule.
     *
     * @return the instance of GoogleServicesModule
     */
    @NonNull
    public static GoogleServicesModule getInstance() {
        return instance;
    }

    /**
     * Provides the client for Google API.
     *
     * @param googleSignInOptions the sign-in options
     * @param context             Context in which the application is running
     * @return the client for Google API
     */
    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static GoogleApiClient provideGoogleApiClient(GoogleSignInOptions googleSignInOptions, ChilindoWeatherApplication context) {
        return new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    /**
     * Provides the client for Google Sign-In.
     *
     * @param googleSignInOptions the sign-in options
     * @param context Context in which the application is running
     * @return the client for Google Sign-In
     */
    @Provides
    @NonNull
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static GoogleSignInClient provideGoogleSignInClient(GoogleSignInOptions googleSignInOptions, ChilindoWeatherApplication context) {
        return GoogleSignIn.getClient(context, googleSignInOptions);
    }

    /**
     * Provides the account the user is signed-in with.
     *
     * @param context Context in which the application is running
     * @return the account the user is signed-in with
     */
    @Provides
    @Nullable
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static GoogleSignInAccount provideGoogleSignInAccount(ChilindoWeatherApplication context) {
        return GoogleSignIn.getLastSignedInAccount(context);
    }

    /**
     * Provides the options for signing-in with Google.
     *
     * @return the options for signing-in with Google
     */
    @Provides
    @NonNull
    @Reusable
    // Safe here as it is a module provider
    @SuppressWarnings("unused")
    static GoogleSignInOptions provideGoogleSignInOptions() {
        return new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
    }
}
