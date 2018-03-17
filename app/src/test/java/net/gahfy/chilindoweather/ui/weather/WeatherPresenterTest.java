package net.gahfy.chilindoweather.ui.weather;

import android.location.Location;
import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import junit.framework.Assert;

import net.gahfy.chilindoweather.model.weather.CurrentWeather;
import net.gahfy.chilindoweather.utils.MockInstance;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static net.gahfy.chilindoweather.utils.ApiUtils.weatherMockPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WeatherPresenterTest {
    private WeatherView weatherView;
    private Location location;
    private WeatherPresenter weatherPresenter;

    @Before
    public void setUp(){
        weatherView = Mockito.mock(WeatherView.class);
        location = Mockito.mock(Location.class);
    }

    @Test
    public void testViewSuccessCall() throws Exception{
        weatherPresenter = new WeatherPresenter(weatherView);
        weatherMockPath = "weather.json";
        weatherPresenter.onLocationAvailable(location);
        verify(weatherView, times(1)).showCurrentWeather(any(CurrentWeather.class));
    }

    @Test
    public void testNeedGeolocation() throws Exception{
        weatherPresenter = new WeatherPresenter(weatherView);
        Assert.assertTrue("Weather presenter has Geolocation on startup", weatherPresenter.needGeolocationonStartup());
    }

    @Test
    public void testViewCreatedWithExistingAccount() throws Exception{
        MockInstance.googleSignInAccount = Mockito.mock(GoogleSignInAccount.class);
        weatherPresenter = new WeatherPresenter(weatherView);
        weatherPresenter.onViewCreated();
        verify(weatherView, times(1)).showProfileInfo(nullable(Uri.class), nullable(String.class), nullable(String.class));
    }

    @Test
    public void testViewCreatedWithoutExistingAccount() throws Exception{
        MockInstance.googleSignInAccount = null;
        weatherPresenter = new WeatherPresenter(weatherView);
        weatherPresenter.onViewCreated();
        verify(weatherView, times(1)).removeUserInfo();
    }
}
