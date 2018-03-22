package net.gahfy.chilindoweather.tests.model.api;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiRain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiRainTest {
    private static final String JSON_COMPLETE = "{" +
            "\"3h\":0.05" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiRain> jsonAdapter = moshi.adapter(ApiRain.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiRain apiRain = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiRain last 3 hours volume from JSON", 0.05, apiRain.getLast3hoursVolume());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiRain apiRain = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiRain last 3 hours volume from JSON", apiRain.getLast3hoursVolume());
    }
}
