package net.gahfy.chilindoweather.model.weather;

import android.os.Parcel;

import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.utils.ContextTestUtils;
import net.gahfy.chilindoweather.utils.UnitUtils;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.Assert.assertEquals;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

public class AbstractWeatherTest {
    private int parcelCounter = 0;

    private AbstractWeather allSetAbstractWeather = new AbstractWeather(
            1521763200,
            R.drawable.broken_clouds,
            R.string.condition_200_description,
            286.0,
            13,
            14.0
    ) {
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
        }
    };


    private AbstractWeather allNullAbstractWeather = new AbstractWeather(
            null,
            R.drawable.broken_clouds,
            R.string.condition_200_description,
            null,
            null,
            null
    ) {
        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
        }
    };

    @Test
    public void testGetTemperature() {
        assertEquals("get temperature", "12°", allSetAbstractWeather.getTemperature(ContextTestUtils.getContext(), UnitUtils.CELSIUS_INDEX));
        assertEquals("get temperature", "55°", allSetAbstractWeather.getTemperature(ContextTestUtils.getContext(), UnitUtils.FAHRENHEIT_INDEX));
        assertEquals("get temperature", "…°", allSetAbstractWeather.getTemperature(ContextTestUtils.getContext(), 3));
        assertEquals("get temperature", "…°", allNullAbstractWeather.getTemperature(ContextTestUtils.getContext(), UnitUtils.CELSIUS_INDEX));
    }

    @Test
    public void testGetWindSpeed() {
        assertEquals("get wind speed", "14 m/s", allSetAbstractWeather.getWindSpeed(ContextTestUtils.getContext(), UnitUtils.METERS_INDEX));
        assertEquals("get wind speed", "31 mph", allSetAbstractWeather.getWindSpeed(ContextTestUtils.getContext(), UnitUtils.MILES_INDEX));
        assertEquals("get wind speed", "… m/s", allSetAbstractWeather.getWindSpeed(ContextTestUtils.getContext(), 3));
        assertEquals("get wind speed", "… m/s", allNullAbstractWeather.getWindSpeed(ContextTestUtils.getContext(), UnitUtils.METERS_INDEX));
    }

    @Test
    public void testGetIconResId() {
        assertEquals("get icon res id", R.drawable.broken_clouds, allSetAbstractWeather.getIconResId());
    }

    @Test
    public void testGetConditionDescriptionId() {
        assertEquals("get description res id", R.string.condition_200_description, allSetAbstractWeather.getConditionDescriptionResId());
    }

    @Test
    public void testGetWindDirection() {
        assertEquals("get wind direction", "13°N", allSetAbstractWeather.getWindDirection(ContextTestUtils.getContext()));
        assertEquals("get wind direction", "…", allNullAbstractWeather.getWindDirection(ContextTestUtils.getContext()));
    }

    @Test
    public void testGetWindDirectionAngle() {
        assertEquals("get wind direction angle", 193, allSetAbstractWeather.getWindDirectionAngle());
        assertEquals("get wind direction angle", 0, allNullAbstractWeather.getWindDirectionAngle());
    }

    @Test
    public void testParcel() {
        assertEquals("describe contents", 0, allSetAbstractWeather.describeContents());

        Parcel parcelAllSet = Mockito.mock(Parcel.class);
        Parcel parcelAllNull = Mockito.mock(Parcel.class);

        allSetAbstractWeather.writeToParcel(parcelAllSet, 0);
        Mockito.verify(parcelAllSet, times(4)).writeByte((byte) 1);
        Mockito.verify(parcelAllSet, times(1)).writeInt(R.string.condition_200_description);
        Mockito.verify(parcelAllSet, times(1)).writeInt(R.drawable.broken_clouds);
        Mockito.verify(parcelAllSet, times(1)).writeInt(13);
        Mockito.verify(parcelAllSet, times(1)).writeDouble(286.0);
        Mockito.verify(parcelAllSet, times(1)).writeDouble(14.0);

        allNullAbstractWeather.writeToParcel(parcelAllNull, 0);
        Mockito.verify(parcelAllNull, times(4)).writeByte((byte) 0);
        Mockito.verify(parcelAllNull, times(1)).writeInt(R.string.condition_200_description);
        Mockito.verify(parcelAllNull, times(1)).writeInt(R.drawable.broken_clouds);
        Mockito.verify(parcelAllNull, times(0)).writeInt(and(not(eq(R.string.condition_200_description)), not(eq(R.drawable.broken_clouds))));
        Mockito.verify(parcelAllNull, times(0)).writeDouble(anyDouble());

        Parcel allNullParcel = Mockito.mock(Parcel.class);
        Mockito.when(allNullParcel.readInt()).thenReturn(0);
        Mockito.when(allNullParcel.readByte()).thenReturn((byte) 0);
        AbstractWeather allNullAbstractWeather = new AbstractWeather(allNullParcel) {
            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
            }
        };

        assertEquals("get temperature", "…°", allNullAbstractWeather.getTemperature(ContextTestUtils.getContext(), UnitUtils.CELSIUS_INDEX));
        assertEquals("get wind speed", "… m/s", allNullAbstractWeather.getWindSpeed(ContextTestUtils.getContext(), UnitUtils.METERS_INDEX));
        assertEquals("get icon res id", 0, allNullAbstractWeather.getIconResId());
        assertEquals("get description res id", 0, allNullAbstractWeather.getConditionDescriptionResId());
        assertEquals("get wind direction", "…", allNullAbstractWeather.getWindDirection(ContextTestUtils.getContext()));
        assertEquals("get wind direction angle", 0, allNullAbstractWeather.getWindDirectionAngle());

        Parcel allSetParcel = Mockito.mock(Parcel.class);
        Mockito.when(allSetParcel.readInt()).thenReturn(0);
        Mockito.when(allSetParcel.readDouble()).thenReturn(0.0);
        Mockito.when(allSetParcel.readByte()).thenReturn((byte) 1);
        AbstractWeather allSetAbstractWeather = new AbstractWeather(allSetParcel) {
            @Override
            public void writeToParcel(Parcel dest, int flags) {
                super.writeToParcel(dest, flags);
            }
        };

        assertEquals("get temperature", "-273°", allSetAbstractWeather.getTemperature(ContextTestUtils.getContext(), UnitUtils.CELSIUS_INDEX));
        assertEquals("get wind speed", "0 m/s", allSetAbstractWeather.getWindSpeed(ContextTestUtils.getContext(), UnitUtils.METERS_INDEX));
        assertEquals("get icon res id", 0, allSetAbstractWeather.getIconResId());
        assertEquals("get description res id", 0, allSetAbstractWeather.getConditionDescriptionResId());
        assertEquals("get wind direction", "0°N", allSetAbstractWeather.getWindDirection(ContextTestUtils.getContext()));
        assertEquals("get wind direction angle", 180, allSetAbstractWeather.getWindDirectionAngle());
    }
}
