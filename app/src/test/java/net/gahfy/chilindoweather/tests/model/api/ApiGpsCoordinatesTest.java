package net.gahfy.chilindoweather.tests.model.api;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiGpsCoordinates;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiGpsCoordinatesTest {
    private static final String JSON_COMPLETE = "{" +
            "\"lat\": 34.9667," +
            "\"lon\": 138.9333" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiGpsCoordinates> jsonAdapter = moshi.adapter(ApiGpsCoordinates.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiGpsCoordinates apiGpsCoordinates = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiGpsCoordinates latitude from JSON", 34.9667, apiGpsCoordinates.getLatitude());
        assertEquals("ApiGpsCoordinates longitude from JSON", 138.9333, apiGpsCoordinates.getLongitude());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiGpsCoordinates apiGpsCoordinates = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiGpsCoordinates latitude from JSON", apiGpsCoordinates.getLatitude());
        assertNull("ApiGpsCoordinates longitude from JSON", apiGpsCoordinates.getLongitude());
    }
}
