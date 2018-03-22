package net.gahfy.chilindoweather.tests.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiForecastItem;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class ApiForecastItemTest {
    private static final String JSON_COMPLETE = "{" +
            "\"dt\": 1521720000," +
            "\"main\": {}," +
            "\"weather\": []," +
            "\"clouds\": {}," +
            "\"wind\": {}," +
            "\"rain\": {}," +
            "\"snow\": {}," +
            "\"sys\": {}," +
            "\"dt_txt\": \"2018-03-22 12:00:00\"" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiForecastItem> jsonAdapter = moshi.adapter(ApiForecastItem.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiForecastItem apiForecastItem = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiForecastItem calculation timestamp from JSON", Integer.valueOf(1521720000), apiForecastItem.getCalculationTimestamp());
        assertNotNull("ApiForecastItem measurements from JSON", apiForecastItem.getMeasurements());
        assertNotNull("ApiForecastItem condition from JSON", apiForecastItem.getCondition());
        assertNotNull("ApiForecastItem cloudiness from JSON", apiForecastItem.getCloudiness());
        assertNotNull("ApiForecastItem wind from JSON", apiForecastItem.getWind());
        assertNotNull("ApiForecastItem rain from JSON", apiForecastItem.getRain());
        assertNotNull("ApiForecastItem snow from JSON", apiForecastItem.getSnow());
        assertNotNull("ApiForecastItem system from JSON", apiForecastItem.getSystem());
        assertEquals("ApiForecastItem calculationDateTime from JSON", "2018-03-22 12:00:00", apiForecastItem.getCalculationDatetime());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiForecastItem apiForecastItem = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiForecastItem calculation timestamp from JSON", apiForecastItem.getCalculationTimestamp());
        assertNull("ApiForecastItem measurements from JSON", apiForecastItem.getMeasurements());
        assertNull("ApiForecastItem condition from JSON", apiForecastItem.getCondition());
        assertNull("ApiForecastItem cloudiness from JSON", apiForecastItem.getCloudiness());
        assertNull("ApiForecastItem wind from JSON", apiForecastItem.getWind());
        assertNull("ApiForecastItem rain from JSON", apiForecastItem.getRain());
        assertNull("ApiForecastItem snow from JSON", apiForecastItem.getSnow());
        assertNull("ApiForecastItem system from JSON", apiForecastItem.getSystem());
        assertNull("ApiForecastItem calculationDateTime from JSON", apiForecastItem.getCalculationDatetime());
    }
}
