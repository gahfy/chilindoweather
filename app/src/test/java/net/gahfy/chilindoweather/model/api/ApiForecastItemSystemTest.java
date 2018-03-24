package net.gahfy.chilindoweather.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiForecastItemSystemTest {
    private static final String JSON_COMPLETE = "{\"pod\":\"n\"}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiForecastItemSystem> jsonAdapter = moshi.adapter(ApiForecastItemSystem.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiForecastItemSystem apiForecastItemSystem = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiForecastItemSystem pod from JSON", "n", apiForecastItemSystem.getPod());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiForecastItemSystem apiForecastItemSystem = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiForecastItemSystem pod from JSON", apiForecastItemSystem.getPod());
    }
}
