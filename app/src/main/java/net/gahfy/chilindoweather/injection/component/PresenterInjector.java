package net.gahfy.chilindoweather.injection.component;

import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.injection.module.ContextModule;
import net.gahfy.chilindoweather.injection.module.GoogleServicesModule;
import net.gahfy.chilindoweather.injection.module.LocationModule;
import net.gahfy.chilindoweather.injection.module.NetworkModule;
import net.gahfy.chilindoweather.injection.module.PermissionModule;
import net.gahfy.chilindoweather.injection.module.PreferencesModule;
import net.gahfy.chilindoweather.ui.base.BaseView;
import net.gahfy.chilindoweather.ui.forecast.ForecastPresenter;
import net.gahfy.chilindoweather.ui.settings.SettingsPresenter;
import net.gahfy.chilindoweather.ui.weather.WeatherPresenter;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Component providing inject() methods for presenters
 */
@Singleton
@Component(modules = {ContextModule.class, PermissionModule.class, NetworkModule.class, LocationModule.class, GoogleServicesModule.class, PreferencesModule.class})
public interface PresenterInjector {
    /**
     * Injects required dependencies into the specified WeatherPresenter.
     *
     * @param weatherPresenter the WeatherPresenter in which to inject the dependencies
     */
    void inject(@NonNull WeatherPresenter weatherPresenter);

    void inject(@NonNull SettingsPresenter settingsPresenter);

    void inject(@NonNull ForecastPresenter forecastPresenter);

    /**
     * The builder to instantiate the component.
     */
    @Component.Builder
    interface Builder {
        /**
         * Builds the PresenterInjector instance.
         *
         * @return the built PresenterInjector instance
         */
        PresenterInjector build();

        /**
         * Sets the specified NetworkModule to the component.
         *
         * @param networkModule the NetworkModule to set to the component
         * @return the current instance of PresenterInjector.Builder
         */
        Builder networkModule(@NonNull NetworkModule networkModule);

        /**
         * Sets the specified PermissionModule to the component.
         *
         * @param presenterModule the PermissionModule to set to the component
         * @return the current instance of PresenterInjector.Builder
         */
        Builder permissionModule(@NonNull PermissionModule presenterModule);

        /**
         * Sets the specified LocationModule to the component.
         *
         * @param locationModule the LocationModule to set to the component
         * @return the current instance of PresenterInjector.Builder
         */
        Builder locationModule(@NonNull LocationModule locationModule);

        /**
         * Sets the GoogleServicesModule to the component.
         *
         * @param googleServicesModule the GoogleServicesModule to set to the component
         * @return the current instance of PresenterInjector.Builder
         */
        Builder googleServicesModule(@NonNull GoogleServicesModule googleServicesModule);

        /**
         * Sets the PreferencesModule to the component.
         *
         * @param preferencesModule the PreferencesModule to set to the component
         * @return the current instance of PresenterInjector.Builder
         */
        Builder preferencesModule(@NonNull PreferencesModule preferencesModule);

        /**
         * Sets the specified ContextModule to the component.
         *
         * @param contextModule the ContextModule to set to the component
         * @return the current instance of PresenterInjector.Builder
         */
        Builder contextModule(@NonNull ContextModule contextModule);

        /**
         * Sets the specified BaseView to the component.
         *
         * @param baseView the BaseView to set to the component
         * @return the current instance of PresenterInjector.Builder
         */
        @BindsInstance
        Builder baseView(@NonNull BaseView baseView);
    }
}
