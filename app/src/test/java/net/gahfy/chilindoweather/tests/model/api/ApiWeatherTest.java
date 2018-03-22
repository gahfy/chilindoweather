package net.gahfy.chilindoweather.tests.model.api;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiWeather;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class ApiWeatherTest {
    private static final String JSON_COMPLETE = "{" +
            "\"coord\": {}," +
            "\"weather\": []," +
            "\"base\": \"stations\"," +
            "\"main\": {}," +
            "\"visibility\": 10000," +
            "\"wind\": {}," +
            "\"clouds\": {}," +
            "\"rain\": {}," +
            "\"snow\": {}," +
            "\"dt\": 1521732600," +
            "\"sys\": {}," +
            "\"id\": 3031137," +
            "\"name\": \"Boulogne-Billancourt\"," +
            "\"message\": \"Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.\"," +
            "\"cod\": 200" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiWeather> jsonAdapter = moshi.adapter(ApiWeather.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiWeather apiWeather = jsonAdapter.fromJson(JSON_COMPLETE);

        assertNotNull("ApiWeather type from JSON", apiWeather.getGpsCoordinates());
        assertNotNull("ApiWeather condition from JSON", apiWeather.getCondition());
        assertEquals("ApiWeather base from JSON", "stations", apiWeather.getBase());
        assertNotNull("ApiWeather measurements from JSON", apiWeather.getMeasurements());
        assertEquals("ApiWeather visibility from JSON", Integer.valueOf(10000), apiWeather.getVisibility());
        assertNotNull("ApiWeather wind from JSON", apiWeather.getWind());
        assertNotNull("ApiWeather clouds from JSON", apiWeather.getClouds());
        assertNotNull("ApiWeather rain from JSON", apiWeather.getRain());
        assertNotNull("ApiWeather snow from JSON", apiWeather.getSnow());
        assertEquals("ApiWeather visibility from JSON", Integer.valueOf(1521732600), apiWeather.getCalculationTimestamp());
        assertNotNull("ApiWeather system from JSON", apiWeather.getSystem());
        assertEquals("ApiWeather city id from JSON", Integer.valueOf(3031137), apiWeather.getCityId());
        assertEquals("ApiWeather city name from JSON", "Boulogne-Billancourt", apiWeather.getCityName());
        assertEquals("ApiWeather error message from JSON", "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.", apiWeather.getErrorMessage());
        assertEquals("ApiWeather http code from JSON", Integer.valueOf(200), apiWeather.getHttpCode());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiWeather apiWeather = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiWeather type from JSON", apiWeather.getGpsCoordinates());
        assertNull("ApiWeather condition from JSON", apiWeather.getCondition());
        assertNull("ApiWeather base from JSON", apiWeather.getBase());
        assertNull("ApiWeather measurements from JSON", apiWeather.getMeasurements());
        assertNull("ApiWeather visibility from JSON", apiWeather.getVisibility());
        assertNull("ApiWeather wind from JSON", apiWeather.getWind());
        assertNull("ApiWeather clouds from JSON", apiWeather.getClouds());
        assertNull("ApiWeather rain from JSON", apiWeather.getRain());
        assertNull("ApiWeather snow from JSON", apiWeather.getSnow());
        assertNull("ApiWeather visibility from JSON", apiWeather.getCalculationTimestamp());
        assertNull("ApiWeather system from JSON", apiWeather.getSystem());
        assertNull("ApiWeather city id from JSON", apiWeather.getCityId());
        assertNull("ApiWeather city name from JSON", apiWeather.getCityName());
        assertNull("ApiWeather error message from JSON", apiWeather.getErrorMessage());
        assertNull("ApiWeather http code from JSON", apiWeather.getHttpCode());
    }
}
