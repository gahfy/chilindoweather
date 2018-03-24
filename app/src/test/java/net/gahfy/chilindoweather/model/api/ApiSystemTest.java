package net.gahfy.chilindoweather.model.api;


import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiSystemTest {
    private static final String JSON_COMPLETE = "{" +
            "\"type\": 1," +
            "\"id\": 5617," +
            "\"message\": 0.0022," +
            "\"country\": \"FR\"," +
            "\"sunrise\": 1521697714," +
            "\"sunset\": 1521742055" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiSystem> jsonAdapter = moshi.adapter(ApiSystem.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiSystem apiSystem = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiSystem type from JSON", Integer.valueOf(1), apiSystem.getType());
        assertEquals("ApiSystem id from JSON", Integer.valueOf(5617), apiSystem.getId());
        assertEquals("ApiSystem message from JSON", 0.0022, apiSystem.getMessage());
        assertEquals("ApiSystem country code from JSON", "FR", apiSystem.getCountryCode());
        assertEquals("ApiSystem sunrise timestamp from JSON", Integer.valueOf(1521697714), apiSystem.getSunriseTimestamp());
        assertEquals("ApiSystem sunset timestamp from JSON", Integer.valueOf(1521742055), apiSystem.getSunsetTimestamp());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiSystem apiSystem = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiSystem type from JSON", apiSystem.getType());
        assertNull("ApiSystem id from JSON", apiSystem.getId());
        assertNull("ApiSystem message from JSON", apiSystem.getMessage());
        assertNull("ApiSystem country code from JSON", apiSystem.getCountryCode());
        assertNull("ApiSystem sunrise timestamp from JSON", apiSystem.getSunriseTimestamp());
        assertNull("ApiSystem sunset timestamp from JSON", apiSystem.getSunsetTimestamp());
    }
}
