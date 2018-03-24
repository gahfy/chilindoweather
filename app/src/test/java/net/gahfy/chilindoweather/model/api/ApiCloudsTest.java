package net.gahfy.chilindoweather.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiCloudsTest {
    /**
     * JSON string with all properties set
     */
    private static final String JSON_COMPLETE = "{\"all\":13}";

    /**
     * JSON string with no property set
     */
    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiClouds> jsonAdapter = moshi.adapter(ApiClouds.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiClouds apiClouds = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiClouds cloudiness from JSON", Integer.valueOf(13), apiClouds.getCloudiness());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiClouds apiClouds = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiClouds cloudiness from JSON", apiClouds.getCloudiness());
    }
}
