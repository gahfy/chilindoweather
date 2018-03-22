package net.gahfy.chilindoweather.tests.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiMeasurements;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiMeasurementsTest {
    private static final String JSON_COMPLETE = "{" +
            "\"temp\": 288.603," +
            "\"temp_min\": 287.603," +
            "\"temp_max\": 289.603," +
            "\"pressure\": 1025.6," +
            "\"sea_level\": 1035," +
            "\"grnd_level\": 1026.6," +
            "\"humidity\": 100," +
            "\"temp_kf\": 0" +
            "}";

    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiMeasurements> jsonAdapter = moshi.adapter(ApiMeasurements.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiMeasurements apiMeasurements = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("APIMeasurements temperature from JSON", 288.603, apiMeasurements.getTemperature());
        assertEquals("APIMeasurements minimum temperature from JSON", 287.603, apiMeasurements.getMinimumTemperature());
        assertEquals("APIMeasurements maximum temperature from JSON", 289.603, apiMeasurements.getMaximumTemperature());
        assertEquals("APIMeasurements pressure from JSON", 1025.6, apiMeasurements.getPressure());
        assertEquals("APIMeasurements sea level pressure from JSON", 1035.0, apiMeasurements.getSeaLevelPressure());
        assertEquals("APIMeasurements ground level pressure from JSON", 1026.6, apiMeasurements.getGroundLevelPressure());
        assertEquals("APIMeasurements humidity from JSON", Integer.valueOf(100), apiMeasurements.getHumidity());
        assertEquals("APIMeasurements temp_kf from JSON", 0.0, apiMeasurements.getTempKf());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiMeasurements apiMeasurements = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("APIMeasurements temperature from JSON", apiMeasurements.getTemperature());
        assertNull("APIMeasurements minimum temperature from JSON", apiMeasurements.getMinimumTemperature());
        assertNull("APIMeasurements maximum temperature from JSON", apiMeasurements.getMaximumTemperature());
        assertNull("APIMeasurements pressure from JSON", apiMeasurements.getPressure());
        assertNull("APIMeasurements sea level pressure from JSON", apiMeasurements.getSeaLevelPressure());
        assertNull("APIMeasurements ground level pressure from JSON", apiMeasurements.getGroundLevelPressure());
        assertNull("APIMeasurements humidity from JSON", apiMeasurements.getHumidity());
        assertNull("APIMeasurements temp_kf from JSON", apiMeasurements.getTempKf());
    }
}
