package net.gahfy.chilindoweather.tests.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiWind;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiWindTest {
    private static final String JSON_COMPLETE = "{" +
            "\"speed\": 5.7," +
            "\"deg\": 300" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiWind> jsonAdapter = moshi.adapter(ApiWind.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiWind apiWind = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiWind speed from JSON", 5.7, apiWind.getSpeed());
        assertEquals("ApiWind direction from JSON", 300.0, apiWind.getDirection());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiWind apiWind = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiWind speed from JSON", apiWind.getSpeed());
        assertNull("ApiWind direction from JSON", apiWind.getDirection());
    }
}
