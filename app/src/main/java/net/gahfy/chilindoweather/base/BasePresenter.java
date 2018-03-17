package net.gahfy.chilindoweather.base;

import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.injection.component.DaggerPresenterInjector;
import net.gahfy.chilindoweather.injection.component.PresenterInjector;
import net.gahfy.chilindoweather.injection.module.ContextModule;
import net.gahfy.chilindoweather.injection.module.GoogleServicesModule;
import net.gahfy.chilindoweather.injection.module.LocationModule;
import net.gahfy.chilindoweather.injection.module.NetworkModule;
import net.gahfy.chilindoweather.injection.module.PermissionModule;
import net.gahfy.chilindoweather.ui.forecast.ForecastPresenter;
import net.gahfy.chilindoweather.ui.settings.SettingsPresenter;
import net.gahfy.chilindoweather.ui.weather.WeatherPresenter;

/**
 * Base presenter any presenter of the application must extend. It provides initial inject required
 * methods
 *
 * @param <V> the type of the View the presenter is based on
 */
public abstract class BasePresenter<V extends BaseView> {
    /**
     * The view associated to the presenter
     */
    @NonNull
    protected final V view;
    @NonNull
    private final PresenterInjector injector;

    /**
     * Instantiates a new BasePresenter and inject required dependencies.
     *
     * @param view the view associated to the presenter to set
     */
    public BasePresenter(@NonNull final V view) {
        this.view = view;

        this.injector = DaggerPresenterInjector
                .builder()
                .baseView(view)
                .contextModule(ContextModule.getInstance())
                .networkModule(NetworkModule.getInstance())
                .permissionModule(PermissionModule.getInstance())
                .locationModule(LocationModule.getInstance())
                .googleServicesModule(GoogleServicesModule.getInstance())
                .build();

        inject();
    }

    /**
     * Injects the required dependencies
     */
    private void inject() {
        if (this instanceof WeatherPresenter) {
            injector.inject((WeatherPresenter) this);
        } else if (this instanceof ForecastPresenter) {
            injector.inject((ForecastPresenter) this);
        } else if (this instanceof SettingsPresenter) {
            injector.inject((SettingsPresenter) this);
        }
    }
}
