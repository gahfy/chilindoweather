package net.gahfy.chilindoweather.tests.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiCity;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class ApiCityTest {
    /**
     * JSON string with all properties set
     */
    private static final String JSON_COMPLETE = "{" +
            "\"id\": 1851632," +
            "\"name\": \"Shuzenji\"," +
            "\"coord\": {}," +
            "\"country\":\"JP\"" +
            "}";

    /** JSON string with no property set */
    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiCity> jsonAdapter = moshi.adapter(ApiCity.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiCity apiCity = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiCity id from JSON", Integer.valueOf(1851632), apiCity.getId());
        assertEquals("ApiCity name from JSON", "Shuzenji", apiCity.getName());
        assertNotNull("ApiCity Coordinates from JSON", apiCity.getGpsCoordinates());
        assertEquals("ApiCity Country from JSON", "JP", apiCity.getCountry());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiCity apiCity = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiCity id from JSON", apiCity.getId());
        assertNull("ApiCity name from JSON", apiCity.getName());
        assertNull("ApiCity Coordinates from JSON", apiCity.getGpsCoordinates());
        assertNull("ApiCity Country from JSON", apiCity.getCountry());
    }
}
