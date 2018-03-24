package net.gahfy.chilindoweather.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;


public class ApiForecastTest {
    private static final String JSON_COMPLETE = "{\"cod\": \"200\"," +
            "\"message\": 0.0048," +
            "\"cnt\": 40," +
            "\"list\": []," +
            "\"city\": {}" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiForecast> jsonAdapter = moshi.adapter(ApiForecast.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiForecast apiForecast = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiForecast http code from JSON", Integer.valueOf(200), apiForecast.getHttpCode());
        assertEquals("ApiForecast message from JSON", "0.0048", apiForecast.getErrorMessage());
        assertEquals("ApiForecast item count from JSON", Integer.valueOf(40), apiForecast.getItemCount());
        assertNotNull("ApiForecast forecast item list from JSON", apiForecast.getForecastItemList());
        assertNotNull("ApiForecast city from JSON", apiForecast.getCity());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiForecast apiForecast = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiForecast http code from JSON", apiForecast.getHttpCode());
        assertNull("ApiForecast message from JSON", apiForecast.getErrorMessage());
        assertNull("ApiForecast item count from JSON", apiForecast.getItemCount());
        assertNull("ApiForecast forecast item list from JSON", apiForecast.getForecastItemList());
        assertNull("ApiForecast city from JSON", apiForecast.getCity());
    }
}
