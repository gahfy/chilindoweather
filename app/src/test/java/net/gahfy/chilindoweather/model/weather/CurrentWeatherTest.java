package net.gahfy.chilindoweather.model.weather;

import android.os.Parcel;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiWeather;
import net.gahfy.chilindoweather.rules.UTCRule;
import net.gahfy.chilindoweather.utils.ContextTestUtils;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class CurrentWeatherTest {
    private static final String JSON_COMPLETE = "{\n" +
            "\"coord\": {" +
            "\"lon\": 2.26," +
            "\"lat\": 48.84" +
            "}," +
            "\"weather\": [" +
            "{" +
            "\"id\": 800," +
            "\"main\": \"Clear\"," +
            "\"description\": \"clear sky\"," +
            "\"icon\": \"01d\"" +
            "}" +
            "]," +
            "\"base\": \"stations\"," +
            "\"main\": {" +
            "\"temp\": 284.58," +
            "\"pressure\": 999," +
            "\"humidity\": 62," +
            "\"temp_min\": 284.15," +
            "\"temp_max\": 285.15" +
            "}," +
            "\"visibility\": 10000," +
            "\"wind\": {" +
            "\"speed\": 1.5" +
            "}," +
            "\"clouds\": {" +
            "\"all\": 0" +
            "}," +
            "\"dt\": 1521894600," +
            "\"sys\": {" +
            "\"type\": 1," +
            "\"id\": 5617," +
            "\"message\": 0.0071," +
            "\"country\": \"FR\"," +
            "\"sunrise\": 1521870279," +
            "\"sunset\": 1521915023" +
            "}," +
            "\"id\": 3031137," +
            "\"name\": \"Boulogne-Billancourt\"," +
            "\"cod\": 200" +
            "}";

    private static final String JSON_EMPTY = "{}";

    @Rule
    public UTCRule utcRule = new UTCRule();

    private CurrentWeather allSetCurrentWeather;
    private CurrentWeather allNullCurrentWeather;

    @Before
    public void setUp() throws Exception {
        final Moshi moshi = new Moshi.Builder().build();
        final JsonAdapter<ApiWeather> jsonAdapter = moshi.adapter(ApiWeather.class);
        allSetCurrentWeather = new CurrentWeather(jsonAdapter.fromJson(JSON_COMPLETE));
        allNullCurrentWeather = new CurrentWeather(jsonAdapter.fromJson(JSON_EMPTY));
    }

    @Test
    public void testGetCalculationDateTime() {
        assertEquals("Calculation Date Time", "03-24-2018 12:30 PM", allSetCurrentWeather.getCalculationDateTime(ContextTestUtils.getContext()));
        assertEquals("Calculation Date Time", "", allNullCurrentWeather.getCalculationDateTime(ContextTestUtils.getContext()));
    }

    @Test
    public void testGetHumidity() {
        assertEquals("Humidity", "62 %", allSetCurrentWeather.getHumidity(ContextTestUtils.getContext()));
        assertEquals("Humidity", "… %", allNullCurrentWeather.getHumidity(ContextTestUtils.getContext()));
    }

    @Test
    public void testGetPressure() {
        assertEquals("Pressure", "999hPa", allSetCurrentWeather.getAtmosphericPressure(ContextTestUtils.getContext()));
        assertEquals("Pressure", "…hPa", allNullCurrentWeather.getAtmosphericPressure(ContextTestUtils.getContext()));
    }

    @Test
    public void testGetCity() {
        assertEquals("City", "Boulogne-Billancourt", allSetCurrentWeather.getCity());
        assertEquals("City", null, allNullCurrentWeather.getCity());
    }

    @Test
    public void testParcels() {
        CurrentWeather[] currentWeathers = CurrentWeather.CREATOR.newArray(20);
        assertEquals("Creator array", 20, currentWeathers.length);

        Parcel allNullParcel = Mockito.mock(Parcel.class);
        Mockito.when(allNullParcel.readInt()).thenReturn(0);
        Mockito.when(allNullParcel.readByte()).thenReturn((byte) 0);
        CurrentWeather allNullCurrentWeather = CurrentWeather.CREATOR.createFromParcel(allNullParcel);

        assertEquals("Calculation Date Time", "", allNullCurrentWeather.getCalculationDateTime(ContextTestUtils.getContext()));
        assertEquals("Humidity", "… %", allNullCurrentWeather.getHumidity(ContextTestUtils.getContext()));
        assertEquals("Pressure", "…hPa", allNullCurrentWeather.getAtmosphericPressure(ContextTestUtils.getContext()));
        assertEquals("City", null, allNullCurrentWeather.getCity());

        Parcel allSetParcel = Mockito.mock(Parcel.class);
        Mockito.when(allSetParcel.readInt()).thenReturn(0);
        Mockito.when(allSetParcel.readDouble()).thenReturn(0.0);
        Mockito.when(allSetParcel.readByte()).thenReturn((byte) 1);
        Mockito.when(allSetParcel.readString()).thenReturn("");
        CurrentWeather allSetCurrentWeather = CurrentWeather.CREATOR.createFromParcel(allSetParcel);
        assertEquals("Calculation Date Time", "01-01-1970 12:00 AM", allSetCurrentWeather.getCalculationDateTime(ContextTestUtils.getContext()));
        assertEquals("Humidity", "0 %", allSetCurrentWeather.getHumidity(ContextTestUtils.getContext()));
        assertEquals("Pressure", "0hPa", allSetCurrentWeather.getAtmosphericPressure(ContextTestUtils.getContext()));
        assertEquals("City", "", allSetCurrentWeather.getCity());

        Parcel writeParcelAllSet = Mockito.mock(Parcel.class);
        this.allSetCurrentWeather.writeToParcel(writeParcelAllSet, 0);
        Mockito.verify(writeParcelAllSet, Mockito.times(6)).writeByte(anyByte());
        Mockito.verify(writeParcelAllSet, Mockito.times(1)).writeInt(62);
        Mockito.verify(writeParcelAllSet, Mockito.times(1)).writeDouble(284.58);
        Mockito.verify(writeParcelAllSet, Mockito.times(1)).writeDouble(999.0);
        Mockito.verify(writeParcelAllSet, Mockito.times(1)).writeString("Boulogne-Billancourt");

        Parcel writeParcelAllNull = Mockito.mock(Parcel.class);
        this.allNullCurrentWeather.writeToParcel(writeParcelAllNull, 0);
        Mockito.verify(writeParcelAllNull, Mockito.times(6)).writeByte(anyByte());
        Mockito.verify(writeParcelAllNull, Mockito.times(0)).writeInt(and(not(eq(R.string.empty)), not(eq(R.drawable.empty))));
        Mockito.verify(writeParcelAllNull, Mockito.times(0)).writeDouble(anyDouble());
        Mockito.verify(writeParcelAllNull, Mockito.times(0)).writeString(anyString());

    }
}
