package net.gahfy.chilindoweather.network;

import net.gahfy.chilindoweather.model.api.ApiForecast;
import net.gahfy.chilindoweather.model.api.ApiWeather;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static net.gahfy.chilindoweather.utils.ApiConstants.QUERY_GPS_LATITUDE;
import static net.gahfy.chilindoweather.utils.ApiConstants.QUERY_GPS_LONGITUDE;

/**
 * The interface which provides methods to get result of the OpenWeatherMap API
 */
public interface OpenWeatherMapApi {
    /**
     * Returns the weather data for the specified position.
     *
     * @param gpsLatitude  the latitude of the position for which to get the weather data
     * @param gpsLongitude the longitude of the position for which to get the weather data
     * @return the weather data for the specified position
     */
    @GET("/data/2.5/weather")
    Observable<ApiWeather> getWeather(
            // Safe because we need to keep same signature as in app
            @SuppressWarnings("unused")
            @Query(QUERY_GPS_LATITUDE) double gpsLatitude,
            // Safe because we need to keep same signature as in app
            @SuppressWarnings("unused")
            @Query(QUERY_GPS_LONGITUDE) double gpsLongitude
    );

    /**
     * Returns the weather forecast for the specified position.
     *
     * @param gpsLatitude  the latitude of the position for which to get the weather forecast
     * @param gpsLongitude the longitude of the position for which to get the weather forecast
     * @return the weather forecast for the specified position
     */
    @GET("/data/2.5/forecast")
    Observable<ApiForecast> getForecast(
            // Safe because we need to keep same signature as in app
            @SuppressWarnings("unused")
            @Query(QUERY_GPS_LATITUDE) double gpsLatitude,
            // Safe because we need to keep same signature as in app
            @SuppressWarnings("unused")
            @Query(QUERY_GPS_LONGITUDE) double gpsLongitude
    );
}
