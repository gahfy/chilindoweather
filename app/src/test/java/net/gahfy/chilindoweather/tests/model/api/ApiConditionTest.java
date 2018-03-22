package net.gahfy.chilindoweather.tests.model.api;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiCondition;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ApiConditionTest {
    /**
     * JSON string with all properties set
     */
    private static final String JSON_COMPLETE = "{" +
            "\"id\":804," +
            "\"main\":\"Clouds\"," +
            "\"description\":\"overcast clouds\"," +
            "\"icon\":\"04d\"" +
            "}";

    /**
     * JSON string with no property set
     */
    private static final String JSON_EMPTY = "{}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiCondition> jsonAdapter = moshi.adapter(ApiCondition.class);

    @Test
    public void testJsonComplete() throws Exception {
        ApiCondition apiCondition = jsonAdapter.fromJson(JSON_COMPLETE);

        assertEquals("ApiCondition id from JSON", Integer.valueOf(804), apiCondition.getId());
        assertEquals("ApiCondition group name from JSON", "Clouds", apiCondition.getGroupName());
        assertEquals("ApiCondition condition name from JSON", "overcast clouds", apiCondition.getConditionName());
        assertEquals("ApiCondition icon id from JSON", "04d", apiCondition.getIconId());
    }

    @Test
    public void testJsonEmpty() throws Exception {
        ApiCondition apiCondition = jsonAdapter.fromJson(JSON_EMPTY);

        assertNull("ApiCondition id from JSON", apiCondition.getId());
        assertNull("ApiCondition group name from JSON", apiCondition.getGroupName());
        assertNull("ApiCondition condition name from JSON", apiCondition.getConditionName());
        assertNull("ApiCondition icon id from JSON", apiCondition.getIconId());
    }
}
