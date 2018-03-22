package net.gahfy.chilindoweather.tests.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiCity;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class ApiCityTest {
    private static final String JSON_COMPLETE = "{" +
            "\"id\": 1851632," +
            "\"name\": \"Shuzenji\"," +
            "\"coord\": {" +
            "\"lat\":34.9667," +
            "\"lon\":138.9333" +
            "}," +
            "\"country\":\"JP\"}";

    private static final String JSON_EMPTY = "{}";

    @Test
    public void testJsonComplete() throws Exception {
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<ApiCity> jsonAdapter = moshi.adapter(ApiCity.class);

        ApiCity apiCity = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiCity id from JSON", Integer.valueOf(1851632), apiCity.getId());
        assertEquals("ApiCity name from JSON", "Shuzenji", apiCity.getName());
        assertNotNull("ApiCity Coordinates from JSON", apiCity.getGpsCoordinates());
        assertEquals("ApiCity Coordinates Latitude from JSON", 34.9667, apiCity.getGpsCoordinates().getLatitude());
        assertEquals("ApiCity Coordinates Longitude from JSON", 138.9333, apiCity.getGpsCoordinates().getLongitude());
        assertEquals("ApiCity Country from JSON", "JP", apiCity.getCountry());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<ApiCity> jsonAdapter = moshi.adapter(ApiCity.class);

        ApiCity apiCity = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiCity id from JSON", apiCity.getId());
        assertNull("ApiCity name from JSON", apiCity.getName());
        assertNull("ApiCity Coordinates from JSON", apiCity.getGpsCoordinates());
        assertNull("ApiCity Country from JSON", apiCity.getCountry());
    }
}
