package net.gahfy.chilindoweather.ui.weather;

import android.location.Location;

import junit.framework.Assert;

import net.gahfy.chilindoweather.model.weather.CurrentWeather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static net.gahfy.chilindoweather.utils.ApiUtils.weatherMockPath;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        verify(weatherView, times(1)).showCurrentWeather(any(CurrentWeather.class), anyInt(), anyInt());
    }

    @Test
    public void testNeedGeolocation() throws Exception{
        weatherPresenter = new WeatherPresenter(weatherView);
        Assert.assertTrue("Weather presenter has Geolocation on startup", weatherPresenter.needGeolocationonStartup());
    }
}
