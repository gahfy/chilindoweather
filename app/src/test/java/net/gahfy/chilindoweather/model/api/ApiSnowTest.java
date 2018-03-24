package net.gahfy.chilindoweather.model.api;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiSnowTest {
    private static final String JSON_COMPLETE = "{" +
            "\"3h\":0.05" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiSnow> jsonAdapter = moshi.adapter(ApiSnow.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiSnow apiSnow = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiSnow last 3 hours volume from JSON", 0.05, apiSnow.getLast3hoursVolume());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiSnow apiSnow = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiSnow last 3 hours volume from JSON", apiSnow.getLast3hoursVolume());
    }
}
