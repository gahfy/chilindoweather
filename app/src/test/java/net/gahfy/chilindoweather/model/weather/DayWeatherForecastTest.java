package net.gahfy.chilindoweather.model.weather;

import android.content.Context;
import android.os.Parcel;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiForecast;
import net.gahfy.chilindoweather.rules.UTCRule;
import net.gahfy.chilindoweather.utils.ContextTestUtils;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

public class DayWeatherForecastTest {
    private static final String JSON_EMPTY = "{}";
    private static final String JSON_NOT_SAME_DAY = "{" +
            "\"list\": [" +
            "{" +
            "\"dt\": 1521720000" +
            "}," +
            "{" +
            "\"dt\": 1521676800" +
            "}," +
            "{" +
            "\"dt\": 1521892800" +
            "}," +
            "null" +
            "]" +
            "}";
    private static final String JSON_FOR_DAY_COMPARATOR = "{" +
            "\"list\": [" +
            "{" +
            "\"dt\": 1521676800" +
            "}," +
            "{" +
            "\"dt\": null" +
            "}," +
            "{" +
            "\"dt\": 1521763200" +
            "}" +
            "]" +
            "}";
    private static final String JSON_FOR_INSTANT_COMPARATOR = "{" +
            "\"list\": [" +
            "{" +
            "\"dt\": 1521763200" +
            "}," +
            "{" +
            "\"dt\": 1521763202" +
            "}," +
            "{" +
            "\"dt\": null" +
            "}," +
            "{" +
            "\"dt\": null" +
            "}," +
            "{" +
            "\"dt\": 1521763201" +
            "}" +
            "]" +
            "}";
    private static final String JSON_FOR_GET_DAY = "{" +
            "\"list\": [" +
            "{" +
            "\"dt\": null" +
            "}," +
            "{" +
            "\"dt\": 1521763200" +
            "}" +
            "]" +
            "}";
    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiForecast> jsonAdapter = moshi.adapter(ApiForecast.class);
    @Rule
    public UTCRule utcRule = new UTCRule();

    @Test
    public void testGetDayWeatherForecastList() throws Exception {
        ApiForecast apiForecastMinimal = jsonAdapter.fromJson(JSON_EMPTY);

        List<DayWeatherForecast> dayWeatherForecastListMinimal = DayWeatherForecast.getDayWeatherForecastList(apiForecastMinimal);
        assertNotNull("List of day weather forecast", dayWeatherForecastListMinimal);
        assertEquals("Size of the list", 0, dayWeatherForecastListMinimal.size());


        ApiForecast apiForecastTwoSameDay = jsonAdapter.fromJson(JSON_NOT_SAME_DAY);

        List<DayWeatherForecast> dayWeatherForecastListTwoSameDay = DayWeatherForecast.getDayWeatherForecastList(apiForecastTwoSameDay);
        assertNotNull("List of day weather forecast", dayWeatherForecastListTwoSameDay);
        assertEquals("Size of the list", 2, dayWeatherForecastListTwoSameDay.size());
        assertEquals("Size of the list of first day", 2, dayWeatherForecastListTwoSameDay.get(0).getForecastList().size());
        assertEquals("Size of the list of second day", 1, dayWeatherForecastListTwoSameDay.get(1).getForecastList().size());
        assertNull("City", dayWeatherForecastListTwoSameDay.get(0).getCity());
        assertEquals("Describe Contents", 0, dayWeatherForecastListTwoSameDay.get(0).describeContents());
    }

    @Test
    public void testDayWeatherForecastComparator() throws Exception {
        ApiForecast apiForecast = jsonAdapter.fromJson(JSON_FOR_DAY_COMPARATOR);

        List<DayWeatherForecast> dayWeatherForecastList = DayWeatherForecast.getDayWeatherForecastList(apiForecast);
        assertNotNull("List of day weather forecast", dayWeatherForecastList);
        assertEquals("Size of the list", 3, dayWeatherForecastList.size());
    }

    @Test
    public void testInstantWeatherForecastComparator() throws Exception {
        ApiForecast apiForecast = jsonAdapter.fromJson(JSON_FOR_INSTANT_COMPARATOR);

        List<DayWeatherForecast> dayWeatherForecastList = DayWeatherForecast.getDayWeatherForecastList(apiForecast);
        assertNotNull("List of day weather forecast", dayWeatherForecastList);
        assertEquals("Size of the list", 2, dayWeatherForecastList.size());
        assertEquals("First item", 2, dayWeatherForecastList.get(0).getForecastList().size());
        assertEquals("Second item", 3, dayWeatherForecastList.get(1).getForecastList().size());
    }

    @Test
    public void testGetDay() throws Exception {
        Context context = ContextTestUtils.getContext();

        ApiForecast apiForecast = jsonAdapter.fromJson(JSON_FOR_GET_DAY);

        List<DayWeatherForecast> dayWeatherForecastList = DayWeatherForecast.getDayWeatherForecastList(apiForecast);
        assertNotNull("List of day weather forecast", dayWeatherForecastList);
        assertEquals("Size of the list", 2, dayWeatherForecastList.size());
        assertEquals("First item", "", dayWeatherForecastList.get(0).getDay(context));
        assertEquals("Second item", "Friday, March 23, 2018", dayWeatherForecastList.get(1).getDay(context));
    }

    @Test
    public void testParcels() throws Exception {
        Context context = ContextTestUtils.getContext();
        ArrayList<InstantWeatherForecast> testInstantWeatherForecasts = new ArrayList<>();

        DayWeatherForecast[] dayWeatherForecasts = DayWeatherForecast.CREATOR.newArray(20);
        assertEquals("size of array", 20, dayWeatherForecasts.length);

        Parcel parcel1 = Mockito.mock(Parcel.class);
        Mockito.when(parcel1.readByte()).thenReturn((byte) 0);
        Mockito.when(parcel1.readString()).thenReturn("Paris");
        Mockito.when(parcel1.createTypedArrayList(InstantWeatherForecast.CREATOR)).thenReturn(testInstantWeatherForecasts);

        DayWeatherForecast dayWeatherForecast1 = DayWeatherForecast.CREATOR.createFromParcel(parcel1);
        assertEquals("test timestamp", "", dayWeatherForecast1.getDay(context));
        assertEquals("city", "Paris", dayWeatherForecast1.getCity());
        assertEquals("list of instants", 0, dayWeatherForecast1.getForecastList().size());

        dayWeatherForecast1.writeToParcel(parcel1, 0);

        Mockito.verify(parcel1).writeByte((byte) 0);
        Mockito.verify(parcel1).writeString("Paris");
        Mockito.verify(parcel1).writeTypedList(testInstantWeatherForecasts);

        Parcel parcel2 = Mockito.mock(Parcel.class);
        Mockito.when(parcel2.readByte()).thenReturn((byte) 1);
        Mockito.when(parcel2.readInt()).thenReturn(1521763200);
        Mockito.when(parcel2.readString()).thenReturn("Paris");
        Mockito.when(parcel2.createTypedArrayList(InstantWeatherForecast.CREATOR)).thenReturn(new ArrayList<InstantWeatherForecast>());

        DayWeatherForecast dayWeatherForecast2 = DayWeatherForecast.CREATOR.createFromParcel(parcel2);
        assertEquals("test timestamp", "Friday, March 23, 2018", dayWeatherForecast2.getDay(context));
        assertEquals("city", "Paris", dayWeatherForecast2.getCity());
        assertEquals("list of instants", 0, dayWeatherForecast2.getForecastList().size());

        dayWeatherForecast2.writeToParcel(parcel2, 0);

        Mockito.verify(parcel2).writeByte((byte) 1);
        Mockito.verify(parcel2).writeInt(1521763200);
        Mockito.verify(parcel2).writeString("Paris");
        Mockito.verify(parcel2).writeTypedList(testInstantWeatherForecasts);
    }
}
