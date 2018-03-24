package net.gahfy.chilindoweather.model.weather;

import android.os.Parcel;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiForecastItem;
import net.gahfy.chilindoweather.rules.UTCRule;
import net.gahfy.chilindoweather.utils.ContextTestUtils;
import net.gahfy.chilindoweather.utils.unit.UnitUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class InstantWeatherForecastTest {
    private static final String JSON_COMPLETE = "{" +
            "\"dt\": 1521925200," +
            "\"main\": {" +
            "\"temp\": 284.64," +
            "\"temp_min\": 284.639," +
            "\"temp_max\": 284.64," +
            "\"pressure\": 1024.04," +
            "\"sea_level\": 1033.65," +
            "\"grnd_level\": 1024.04," +
            "\"humidity\": 100," +
            "\"temp_kf\": 0" +
            "}," +
            "\"weather\": [" +
            "{" +
            "\"id\": 500," +
            "\"main\": \"Rain\"," +
            "\"description\": \"light rain\"," +
            "\"icon\": \"10d\"" +
            "}" +
            "]," +
            "\"clouds\": {" +
            "\"all\": 0" +
            "}," +
            "\"wind\": {" +
            "\"speed\": 3.61," +
            "\"deg\": 315.501" +
            "}," +
            "\"rain\": {" +
            "\"3h\": 0.0025" +
            "}," +
            "\"sys\": {" +
            "\"pod\": \"d\"" +
            "}," +
            "\"dt_txt\": \"2018-03-24 21:00:00\"" +
            "}";

    private static final String JSON_EMPTY = "{}";
    @Rule
    public UTCRule utcRule = new UTCRule();
    private InstantWeatherForecast allSetInstantWeatherForecast;
    private InstantWeatherForecast allNullInstantWeatherForecast;

    @Before
    public void setUp() throws Exception {
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<ApiForecastItem> jsonAdapter = moshi.adapter(ApiForecastItem.class);
        allSetInstantWeatherForecast = new InstantWeatherForecast(jsonAdapter.fromJson(JSON_COMPLETE));
        allNullInstantWeatherForecast = new InstantWeatherForecast(jsonAdapter.fromJson(JSON_EMPTY));
    }

    @Test
    public void testCalculationTimestamp() throws Exception {
        assertEquals("calculation timestamp", "09 PM", allSetInstantWeatherForecast.getCalculationTime(ContextTestUtils.getContext()));
        assertEquals("calculation timestamp", "", allNullInstantWeatherForecast.getCalculationTime(ContextTestUtils.getContext()));
    }

    @Test
    public void testParcels() throws Exception {
        InstantWeatherForecast[] instantWeatherForecasts = InstantWeatherForecast.CREATOR.newArray(20);
        assertEquals("size of array creator", 20, instantWeatherForecasts.length);

        assertEquals("describe contents", 0, allSetInstantWeatherForecast.describeContents());

        Parcel parcelAllSet = Mockito.mock(Parcel.class);
        Parcel parcelAllNull = Mockito.mock(Parcel.class);

        allSetInstantWeatherForecast.writeToParcel(parcelAllSet, 0);
        Mockito.verify(parcelAllSet, times(4)).writeByte((byte) 1);
        Mockito.verify(parcelAllSet, times(1)).writeInt(R.string.condition_500_description);
        Mockito.verify(parcelAllSet, times(1)).writeInt(R.drawable.rain_day);
        Mockito.verify(parcelAllSet, times(1)).writeInt(1521925200);
        Mockito.verify(parcelAllSet, times(1)).writeDouble(284.64);
        Mockito.verify(parcelAllSet, times(1)).writeDouble(3.61);

        allNullInstantWeatherForecast.writeToParcel(parcelAllNull, 0);
        Mockito.verify(parcelAllNull, times(4)).writeByte((byte) 0);
        Mockito.verify(parcelAllNull, times(1)).writeInt(R.string.empty);
        Mockito.verify(parcelAllNull, times(1)).writeInt(R.drawable.empty);
        Mockito.verify(parcelAllNull, times(0)).writeInt(and(not(eq(R.string.empty)), not(eq(R.drawable.empty))));
        Mockito.verify(parcelAllNull, times(0)).writeDouble(anyDouble());

        Parcel allNullParcel = Mockito.mock(Parcel.class);
        Mockito.when(allNullParcel.readInt()).thenReturn(0);
        Mockito.when(allNullParcel.readByte()).thenReturn((byte) 0);
        InstantWeatherForecast allNullInstantWeatherForecast = InstantWeatherForecast.CREATOR.createFromParcel(allNullParcel);

        assertEquals("get temperature", "…°", allNullInstantWeatherForecast.getTemperature(ContextTestUtils.getContext(), UnitUtils.CELSIUS_INDEX));
        assertEquals("get wind speed", "… m/s", allNullInstantWeatherForecast.getWindSpeed(ContextTestUtils.getContext(), UnitUtils.METERS_INDEX));
        assertEquals("get icon res id", 0, allNullInstantWeatherForecast.getIconResId());
        assertEquals("get description res id", 0, allNullInstantWeatherForecast.getConditionDescriptionResId());
        assertEquals("get wind direction", "…", allNullInstantWeatherForecast.getWindDirection(ContextTestUtils.getContext()));
        assertEquals("get wind direction angle", 0, allNullInstantWeatherForecast.getWindDirectionAngle());

        Parcel allSetParcel = Mockito.mock(Parcel.class);
        Mockito.when(allSetParcel.readInt()).thenReturn(0);
        Mockito.when(allSetParcel.readDouble()).thenReturn(0.0);
        Mockito.when(allSetParcel.readByte()).thenReturn((byte) 1);
        AbstractWeather allSetInstantWeatherForecast = InstantWeatherForecast.CREATOR.createFromParcel(allSetParcel);

        assertEquals("get temperature", "-273°", allSetInstantWeatherForecast.getTemperature(ContextTestUtils.getContext(), UnitUtils.CELSIUS_INDEX));
        assertEquals("get wind speed", "0 m/s", allSetInstantWeatherForecast.getWindSpeed(ContextTestUtils.getContext(), UnitUtils.METERS_INDEX));
        assertEquals("get icon res id", 0, allSetInstantWeatherForecast.getIconResId());
        assertEquals("get description res id", 0, allSetInstantWeatherForecast.getConditionDescriptionResId());
        assertEquals("get wind direction", "0°N", allSetInstantWeatherForecast.getWindDirection(ContextTestUtils.getContext()));
        assertEquals("get wind direction angle", 180, allSetInstantWeatherForecast.getWindDirectionAngle());
    }
}
