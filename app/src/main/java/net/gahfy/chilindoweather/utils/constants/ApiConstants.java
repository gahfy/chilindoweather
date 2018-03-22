package net.gahfy.chilindoweather.utils.constants;

/**
 * Constants related to the OpenWeatherMap API
 */
public final class ApiConstants {
    /**
     * The base URL of the API
     */
    public static final String BASE_URL = "http://api.openweathermap.org/";

    /**
     * The name of the GET query for the API key
     */
    public static final String QUERY_API_KEY = "appid";
    /**
     * The name of the GET query for the latitude of a GPS position
     */
    public static final String QUERY_GPS_LATITUDE = "lat";
    /**
     * The name of the GET query for the longitude of a GPS position
     */
    public static final String QUERY_GPS_LONGITUDE = "lon";

    /**
     * The name of the JSON field for the GPS coordinates to which weather forecast applies
     */
    public static final String JSON_WEATHER_GPS_COORDINATES = "coord";
    /**
     * The name of the JSON field for the weather condition
     */
    public static final String JSON_WEATHER_CONDITION = "weather";
    /**
     * The name of the JSON field for the API base value
     */
    public static final String JSON_WEATHER_BASE = "base";
    /**
     * The name of the JSON field for the weather measurements
     */
    public static final String JSON_WEATHER_MEASUREMENTS = "main";
    /**
     * The name of the JSON field for the visibility in meters
     */
    public static final String JSON_WEATHER_VISIBILITY = "visibility";
    /**
     * The name of the JSON field for the wind data
     */
    public static final String JSON_WEATHER_WIND = "wind";
    /**
     * The name of the JSON field for the cloudiness data
     */
    public static final String JSON_WEATHER_CLOUDS = "clouds";
    /**
     * The name of the JSON field for the rain data
     */
    public static final String JSON_WEATHER_RAIN = "rain";
    /**
     * The name of the JSON field for the snow data
     */
    public static final String JSON_WEATHER_SNOW = "snow";
    /**
     * The name of the JSON field for the timestamp of data calculation
     */
    public static final String JSON_WEATHER_CALCULATION_TIMESTAMP = "dt";
    /**
     * The name of the JSON field for the system data
     */
    public static final String JSON_WEATHER_SYSTEM = "sys";
    /**
     * The name of the JSON field for the unique identifier of the city
     */
    public static final String JSON_WEATHER_CITY_ID = "id";
    /**
     * The name of the JSON field for the name of the city
     */
    public static final String JSON_WEATHER_CITY_NAME = "name";
    /**
     * The name of the JSON field for the HTTP code of the response
     */
    public static final String JSON_WEATHER_HTTP_CODE = "cod";
    /**
     * The name of the JSON field for the error message
     */
    public static final String JSON_WEATHER_ERROR_MESSAGE = "message";

    /**
     * The name of the JSON field for the HTTP code of the response
     */
    public static final String JSON_FORECAST_HTTP_CODE = "cod";
    /**
     * The name of the JSON field for the error message
     */
    public static final String JSON_FORECAST_ERROR_MESSAGE = "message";
    /**
     * The name of the JSON field for the number of items
     */
    public static final String JSON_FORECAST_ITEM_COUNT = "cnt";
    /**
     * The name of the JSON field for the list of weather forecast
     */
    public static final String JSON_FORECAST_FORECAST_LIST = "list";
    /**
     * The name of the JSON field for the city
     */
    public static final String JSON_FORECAST_CITY = "city";

    /**
     * The name of the JSON field for the calculation timestamp
     */
    public static final String JSON_FORECAST_ITEM_CALCULATION_TIMESTAMP = "dt";
    /**
     * The name of the JSON field for the measurements
     */
    public static final String JSON_FORECAST_ITEM_MEASUREMENTS = "main";
    /**
     * The name of the JSON field for the condition
     */
    public static final String JSON_FORECAST_ITEM_CONDITION = "weather";
    /**
     * The name of the JSON field for the cloudiness
     */
    public static final String JSON_FORECAST_ITEM_CLOUDINESS = "clouds";
    /**
     * The name of the JSON field for the wind
     */
    public static final String JSON_FORECAST_ITEM_WIND = "wind";
    /**
     * The name of the JSON field for the rain
     */
    public static final String JSON_FORECAST_ITEM_RAIN = "rain";
    /**
     * The name of the JSON field for the snow
     */
    public static final String JSON_FORECAST_ITEM_SNOW = "snow";
    /**
     * The name of the JSON field for the calculation datetime
     */
    public static final String JSON_FORECAST_ITEM_CALCULATION_DATETIME = "dt_txt";
    /**
     * The name of the JSON field for API sys value
     */
    public static final String JSON_FORECAST_ITEM_SYSTEM = "sys";

    /**
     * The name of the JSON field for API pod value
     */
    public static final String JSON_FORECAST_ITEM_SYSTEM_POD = "pod";

    /**
     * The name of the JSON field for the unique identifier of the city
     */
    public static final String JSON_CITY_ID = "id";
    /**
     * The name of the JSON field for the name of the city
     */
    public static final String JSON_CITY_NAME = "name";
    /**
     * The name of the JSON field for the gps coordinates of the city
     */
    public static final String JSON_CITY_GPS_COORDINATES = "coord";
    /**
     * The name of the JSON field for the country code of the city
     */
    public static final String JSON_CITY_COUNTRY = "country";

    /**
     * The name of the JSON field for latitude of GPS coordinates
     */
    public static final String JSON_GPS_COORDINATES_LATITUDE = "lat";
    /**
     * The name of the JSON field for longitude of GPS coordinates
     */
    public static final String JSON_GPS_COORDINATES_LONGITUDE = "lon";

    /**
     * The name of the JSON field for the unique identifier of weather condition
     */
    public static final String JSON_CONDITION_ID = "id";
    /**
     * The name of the JSON field for the group of the weather parameters (Rain, Snow, Extreme, .)
     */
    public static final String JSON_CONDITION_GROUP_NAME = "main";
    /**
     * The name of the JSON field for the weather condition within the group
     */
    public static final String JSON_CONDITION_CONDITION_NAME = "description";
    /**
     * The name of the JSON field for the identifier of the weather icon
     */
    public static final String JSON_CONDITION_ICON_ID = "icon";

    /**
     * The name of the JSON field for the temperature
     */
    public static final String JSON_MEASUREMENTS_TEMPERATURE = "temp";
    /**
     * The name of the JSON field for the atmospheric pressure
     */
    public static final String JSON_MEASUREMENTS_PRESSURE = "pressure";
    /**
     * The name of the JSON field for the humidity
     */
    public static final String JSON_MEASUREMENTS_HUMIDITY = "humidity";
    /**
     * The name of the JSON field for the minimum temperature
     */
    public static final String JSON_MEASUREMENTS_MINIMUM_TEMPERATURE = "temp_min";
    /**
     * The name of the JSON field for the maximum temperature
     */
    public static final String JSON_MEASUREMENTS_MAXIMUM_TEMPERATURE = "temp_max";
    /**
     * The name of the JSON field for the atmospheric pressure on the sea level
     */
    public static final String JSON_MEASUREMENTS_SEA_LEVEL_PRESSURE = "sea_level";
    /**
     * The name of the JSON field for the atmospheric pressure on the ground level
     */
    public static final String JSON_MEASUREMENTS_GROUND_LEVEL_PRESSURE = "grnd_level";
    /**
     * The name of the JSON field for the temp_kf value
     */
    public static final String JSON_MEASUREMENTS_TEMP_KF = "temp_kf";

    /**
     * The name of the JSON field for the wind speed
     */
    public static final String JSON_WIND_SPEED = "speed";
    /**
     * The name of the JSON field for the wind direction
     */
    public static final String JSON_WIND_DIRECTION = "deg";

    /**
     * The name of the JSON field for the cloudiness
     */
    public static final String JSON_CLOUDS_CLOUDINESS = "all";

    /**
     * The name of the JSON field for the rain volume of the last 3 hours
     */
    public static final String JSON_RAIN_LAST_3_HOURS_VOLUME = "3h";

    /**
     * The name of the JSON field for the snow volume of the last 3 hours
     */
    public static final String JSON_SNOW_LAST_3_HOURS_VOLUME = "3h";

    /**
     * The name of the JSON field for the API type value
     */
    public static final String JSON_SYSTEM_TYPE = "type";
    /**
     * The name of the JSON field for the API id value
     */
    public static final String JSON_SYSTEM_ID = "id";
    /**
     * The name of the JSON field for the API message value
     */
    public static final String JSON_SYSTEM_MESSAGE = "message";
    /**
     * The name of the JSON field for the country code
     */
    public static final String JSON_SYSTEM_COUNTRY_CODE = "country";
    /**
     * The name of the JSON field for the time of sunrise
     */
    public static final String JSON_SYSTEM_SUNRISE_TIMESTAMP = "sunrise";
    /**
     * The name of the JSON field for the time of sunset
     */
    public static final String JSON_SYSTEM_SUNSET_TIMESTAMP = "sunset";
}
