package net.gahfy.chilindoweather.model.weather;


import net.gahfy.chilindoweather.R;
import net.gahfy.chilindoweather.model.api.ApiCondition;
import net.gahfy.chilindoweather.model.api.ApiMeasurements;
import net.gahfy.chilindoweather.model.api.ApiWeather;
import net.gahfy.chilindoweather.model.api.ApiWind;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class CurrentWeatherTest {
    @Test
    public void testGetCityValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);

        Mockito.when(apiWeather.getCityName()).thenReturn("Paris");
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiWeather.getCityName() = Paris so currentWeather.getCity() = Paris", currentWeather.getCity(), "Paris");

        Mockito.when(apiWeather.getCityName()).thenReturn(null);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiWeather.getCityName() = null so currentWeather.getCity() = null", currentWeather.getCity(), null);
    }

    @Test
    public void testGetAtmosphericPressureValue() throws Exception{
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiMeasurements apiMeasurements = Mockito.mock(ApiMeasurements.class);

        Mockito.when(apiWeather.getMeasurements()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getMeasurements() = null so currentWeather.getAtmosphericPressure() = null", currentWeather.getAtmosphericPressure(), null);

        Mockito.when(apiMeasurements.getPressure()).thenReturn(null);
        Mockito.when(apiMeasurements.getGroundLevelPressure()).thenReturn(null);
        Mockito.when(apiMeasurements.getSeaLevelPressure()).thenReturn(null);
        Mockito.when(apiWeather.getMeasurements()).thenReturn(apiMeasurements);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("all pressure = null so currentWeather.getAtmosphericPressure() = null", currentWeather.getAtmosphericPressure(), null);


        Mockito.when(apiMeasurements.getPressure()).thenReturn(null);
        Mockito.when(apiMeasurements.getGroundLevelPressure()).thenReturn(null);
        Mockito.when(apiMeasurements.getSeaLevelPressure()).thenReturn(3.99);
        Mockito.when(apiWeather.getMeasurements()).thenReturn(apiMeasurements);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("sea level pressure = 3.99, all other pressure - null so currentWeather.getAtmosphericPressure() = 3", currentWeather.getAtmosphericPressure(), new Integer(3));

        Mockito.when(apiMeasurements.getPressure()).thenReturn(null);
        Mockito.when(apiMeasurements.getGroundLevelPressure()).thenReturn(4.99);
        Mockito.when(apiMeasurements.getSeaLevelPressure()).thenReturn(3.99);
        Mockito.when(apiWeather.getMeasurements()).thenReturn(apiMeasurements);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("sea level pressure = 3.99, groundPressure = 4.99, pressure - null so currentWeather.getAtmosphericPressure() = 4", currentWeather.getAtmosphericPressure(), new Integer(4));

        Mockito.when(apiMeasurements.getPressure()).thenReturn(7.98);
        Mockito.when(apiMeasurements.getGroundLevelPressure()).thenReturn(4.99);
        Mockito.when(apiMeasurements.getSeaLevelPressure()).thenReturn(3.99);
        Mockito.when(apiWeather.getMeasurements()).thenReturn(apiMeasurements);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("sea level pressure = 3.99, groundPressure = 4.99, pressure - 7.98 so currentWeather.getAtmosphericPressure() = 7", currentWeather.getAtmosphericPressure(), new Integer(7));
    }

    @Test
    public void testGetHumidityValue() throws Exception{
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiMeasurements apiMeasurements = Mockito.mock(ApiMeasurements.class);

        Mockito.when(apiWeather.getMeasurements()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getMeasurements() = null so currentWeather.getHumidity() = null", currentWeather.getHumidity(), null);

        Mockito.when(apiMeasurements.getHumidity()).thenReturn(null);
        Mockito.when(apiWeather.getMeasurements()).thenReturn(apiMeasurements);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getMeasurements().getHumidity() = null so currentWeather.getHumidity() = null", currentWeather.getHumidity(), null);

        Mockito.when(apiMeasurements.getHumidity()).thenReturn(3);
        Mockito.when(apiWeather.getMeasurements()).thenReturn(apiMeasurements);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getMeasurements().getHumidity() = 3 so currentWeather.getHumidity() = 3", currentWeather.getHumidity(), new Integer(3));
    }

    @Test
    public void testGetWindSpeedValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiWind apiWind = Mockito.mock(ApiWind.class);

        Mockito.when(apiWeather.getWind()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind() = null so currentWeather.getWindSpeed() = null", currentWeather.getWindSpeed(), null);

        Mockito.when(apiWind.getSpeed()).thenReturn(null);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getSpeed() = null so currentWeather.getWindSpeed() = null", currentWeather.getWindSpeed(), null);

        Mockito.when(apiWind.getSpeed()).thenReturn(3.999);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getSpeed() = 3.999 so currentWeather.getWindSpeed() = 3", currentWeather.getWindSpeed(), new Integer(3));
    }

    @Test
    public void testGetWindDirectionOrientationValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiWind apiWind = Mockito.mock(ApiWind.class);

        Mockito.when(apiWeather.getWind()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind() = null so currentWeather.getWindDirectionOrientation() = null", currentWeather.getWindDirectionOrientation(), 0);

        Mockito.when(apiWind.getDirection()).thenReturn(null);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = null so currentWeather.getWindDirectionOrientation() = null", currentWeather.getWindDirectionOrientation(), 0);

        Mockito.when(apiWind.getDirection()).thenReturn(3.999);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = 3.999 so currentWeather.getWindDirectionOrientation() = R.string.north_abbr", currentWeather.getWindDirectionOrientation(), R.string.north_abbr);

        Mockito.when(apiWind.getDirection()).thenReturn(93.999);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = 3.999 so currentWeather.getWindDirectionOrientation() = R.string.east_abbr", currentWeather.getWindDirectionOrientation(), R.string.east_abbr);

        Mockito.when(apiWind.getDirection()).thenReturn(183.999);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = 3.999 so currentWeather.getWindDirectionOrientation() = R.string.south_abbr", currentWeather.getWindDirectionOrientation(), R.string.south_abbr);

        Mockito.when(apiWind.getDirection()).thenReturn(273.999);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = 3.999 so currentWeather.getWindDirectionOrientation() = R.string.west_abbr", currentWeather.getWindDirectionOrientation(), R.string.west_abbr);

        Mockito.when(apiWind.getDirection()).thenReturn(389.99);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = 389.99 so currentWeather.getWindDirectionOrientation() = 0", currentWeather.getWindDirectionOrientation(), 0);
    }

    @Test
    public void testGetWindDirectionNormalizedValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiWind apiWind = Mockito.mock(ApiWind.class);

        Mockito.when(apiWeather.getWind()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind() = null so currentWeather.getWindDirectionNormalized() = null", currentWeather.getWindDirectionNormalized(), null);

        Mockito.when(apiWind.getDirection()).thenReturn(null);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = null so currentWeather.getWindDirectionNormalized() = null", currentWeather.getWindDirectionNormalized(), null);

        Mockito.when(apiWind.getDirection()).thenReturn(3.999);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = 3.999 so currentWeather.getWindDirectionNormalized() = 3", currentWeather.getWindDirectionNormalized(), new Integer(3));

        Mockito.when(apiWind.getDirection()).thenReturn(183.999);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = 183.999 so currentWeather.getWindDirectionNormalized() = 3", currentWeather.getWindDirectionNormalized(), new Integer(3));
    }

    @Test
    public void testGetWindDirectionValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiWind apiWind = Mockito.mock(ApiWind.class);

        Mockito.when(apiWeather.getWind()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind() = null so currentWeather.getWindDirection() = null", currentWeather.getWindDirection(), null);

        Mockito.when(apiWind.getDirection()).thenReturn(null);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = null so currentWeather.getWindDirection() = null", currentWeather.getWindDirection(), null);

        Mockito.when(apiWind.getDirection()).thenReturn(3.999);
        Mockito.when(apiWeather.getWind()).thenReturn(apiWind);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getWind().getDirection() = 3.999 so currentWeather.getWindDirection() = 3", currentWeather.getWindDirection(), new Integer(3));
    }

    @Test
    public void testGetTemperatureValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiMeasurements apiMeasurements = Mockito.mock(ApiMeasurements.class);

        Mockito.when(apiWeather.getMeasurements()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getMeasurements() = null so currentWeather.getTemperature() = null", currentWeather.getTemperature(), null);

        Mockito.when(apiMeasurements.getTemperature()).thenReturn(null);
        Mockito.when(apiWeather.getMeasurements()).thenReturn(apiMeasurements);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getMeasurements().getTemperature() = null so currentWeather.getTemperature() = null", currentWeather.getTemperature(), null);

        Mockito.when(apiMeasurements.getTemperature()).thenReturn(3.999);
        Mockito.when(apiWeather.getMeasurements()).thenReturn(apiMeasurements);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("getMeasurements().getTemperature() = 3.999 so currentWeather.getTemperature() = 3", currentWeather.getTemperature(), new Integer(3));
    }

    @Test
    public void testGetCalculationTimestampValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);

        Mockito.when(apiWeather.getCalculationTimestamp()).thenReturn(1521313422);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiWeather.getCalculationTimestamp() = 1521313422 so currentWeather.getCalculationTimestamp() = 1521313422", currentWeather.getCalculationTimestamp(), new Integer(1521313422));

        Mockito.when(apiWeather.getCalculationTimestamp()).thenReturn(null);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiWeather.getCalculationTimestamp() = null so currentWeather.getCalculationTimestamp() = null", currentWeather.getCalculationTimestamp(), null);
    }

    @Test
    public void testGetIconResIdValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiCondition apiCondition = Mockito.mock(ApiCondition.class);
        ApiCondition[] apiConditions = new ApiCondition[]{apiCondition};
        Mockito.when(apiWeather.getCondition()).thenReturn(apiConditions);

        Mockito.when(apiWeather.getCondition()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition = null so currentWeather.getIconResId() = 0", currentWeather.getIconResId(), 0);

        apiConditions = new ApiCondition[0];
        Mockito.when(apiWeather.getCondition()).thenReturn(apiConditions);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.length = 0 so currentWeather.getIconResId() = 0", currentWeather.getIconResId(), 0);

        apiConditions = new ApiCondition[]{apiCondition};
        Mockito.when(apiWeather.getCondition()).thenReturn(apiConditions);
        Mockito.when(apiCondition.getIconId()).thenReturn(null);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = null so currentWeather.getIconResId() = 0", currentWeather.getIconResId(), 0);

        Mockito.when(apiCondition.getIconId()).thenReturn("00d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 00d so currentWeather.getIconResId() = 0", currentWeather.getIconResId(), 0);

        Mockito.when(apiCondition.getIconId()).thenReturn("01d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 01d so currentWeather.getIconResId() = R.drawable.clear_sky_day", currentWeather.getIconResId(), R.drawable.clear_sky_day);

        Mockito.when(apiCondition.getIconId()).thenReturn("01n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 01n so currentWeather.getIconResId() = R.drawable.clear_sky_night", currentWeather.getIconResId(), R.drawable.clear_sky_night);

        Mockito.when(apiCondition.getIconId()).thenReturn("02d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 02d so currentWeather.getIconResId() = R.drawable.few_clouds_day", currentWeather.getIconResId(), R.drawable.few_clouds_day);

        Mockito.when(apiCondition.getIconId()).thenReturn("02n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 02n so currentWeather.getIconResId() = R.drawable.few_clouds_night", currentWeather.getIconResId(), R.drawable.few_clouds_night);

        Mockito.when(apiCondition.getIconId()).thenReturn("03d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 03d so currentWeather.getIconResId() = R.drawable.scattered_clouds", currentWeather.getIconResId(), R.drawable.scattered_clouds);

        Mockito.when(apiCondition.getIconId()).thenReturn("03n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 03n so currentWeather.getIconResId() = R.drawable.scattered_clouds", currentWeather.getIconResId(), R.drawable.scattered_clouds);

        Mockito.when(apiCondition.getIconId()).thenReturn("04d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 04d so currentWeather.getIconResId() = R.drawable.broken_clouds", currentWeather.getIconResId(), R.drawable.broken_clouds);

        Mockito.when(apiCondition.getIconId()).thenReturn("04n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 04n so currentWeather.getIconResId() = R.drawable.broken_clouds", currentWeather.getIconResId(), R.drawable.broken_clouds);

        Mockito.when(apiCondition.getIconId()).thenReturn("09d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 09d so currentWeather.getIconResId() = R.drawable.shower_rain", currentWeather.getIconResId(), R.drawable.shower_rain);

        Mockito.when(apiCondition.getIconId()).thenReturn("09n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 09n so currentWeather.getIconResId() = R.drawable.shower_rain", currentWeather.getIconResId(), R.drawable.shower_rain);

        Mockito.when(apiCondition.getIconId()).thenReturn("10d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 10d so currentWeather.getIconResId() = R.drawable.rain_day", currentWeather.getIconResId(), R.drawable.rain_day);

        Mockito.when(apiCondition.getIconId()).thenReturn("10n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 10n so currentWeather.getIconResId() = R.drawable.rain_night", currentWeather.getIconResId(), R.drawable.rain_night);

        Mockito.when(apiCondition.getIconId()).thenReturn("11d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 11d so currentWeather.getIconResId() = R.drawable.thunderstorm", currentWeather.getIconResId(), R.drawable.thunderstorm);

        Mockito.when(apiCondition.getIconId()).thenReturn("11n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 11n so currentWeather.getIconResId() = R.drawable.thunderstorm", currentWeather.getIconResId(), R.drawable.thunderstorm);

        Mockito.when(apiCondition.getIconId()).thenReturn("13d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 13d so currentWeather.getIconResId() = R.drawable.snow", currentWeather.getIconResId(), R.drawable.snow);

        Mockito.when(apiCondition.getIconId()).thenReturn("13n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 13n so currentWeather.getIconResId() = R.drawable.snow", currentWeather.getIconResId(), R.drawable.snow);

        Mockito.when(apiCondition.getIconId()).thenReturn("50d");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 50d so currentWeather.getIconResId() = R.drawable.mist", currentWeather.getIconResId(), R.drawable.mist);

        Mockito.when(apiCondition.getIconId()).thenReturn("50n");
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getIconId() = 50n so currentWeather.getIconResId() = R.drawable.mist", currentWeather.getIconResId(), R.drawable.mist);
    }

    @Test
    public void testGetConditionDescriptionResIdValue() throws Exception {
        ApiWeather apiWeather = Mockito.mock(ApiWeather.class);
        ApiCondition apiCondition = Mockito.mock(ApiCondition.class);
        ApiCondition[] apiConditions = new ApiCondition[]{apiCondition};
        Mockito.when(apiWeather.getCondition()).thenReturn(apiConditions);


        Mockito.when(apiWeather.getCondition()).thenReturn(null);
        CurrentWeather currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition = null so currentWeather.getConditionDescriptionResId() = 0", currentWeather.getConditionDescriptionResId(), 0);

        apiConditions = new ApiCondition[0];
        Mockito.when(apiWeather.getCondition()).thenReturn(apiConditions);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.length = 0 so currentWeather.getConditionDescriptionResId() = 0", currentWeather.getConditionDescriptionResId(), 0);

        apiConditions = new ApiCondition[]{apiCondition};
        Mockito.when(apiWeather.getCondition()).thenReturn(apiConditions);
        Mockito.when(apiCondition.getId()).thenReturn(null);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = null so currentWeather.getConditionDescriptionResId() = 0", currentWeather.getConditionDescriptionResId(), 0);

        Mockito.when(apiCondition.getId()).thenReturn(100);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 100 so currentWeather.getConditionDescriptionResId() = 0", currentWeather.getConditionDescriptionResId(), 0);


        /*
         In order to generate this block, copy paste block of conditions from strings.xml.
         Then replace all, with regex checked:

         Search pattern:
         <string name="condition_([\d]{3})_description">[^<]+</string>

         Replace by:
         Mockito.when(apiCondition.getId()).thenReturn($1);\ncurrentWeather = new CurrentWeather(apiWeather);\nassertEquals("apiCondition.getId() = $1 so currentWeather.getConditionDescriptionResId() = R.string.condition_$1_description", currentWeather.getConditionDescriptionResId(), R.string.condition_$1_description);
         */
        
        Mockito.when(apiCondition.getId()).thenReturn(200);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 200 so currentWeather.getConditionDescriptionResId() = R.string.condition_200_description", currentWeather.getConditionDescriptionResId(), R.string.condition_200_description);

        Mockito.when(apiCondition.getId()).thenReturn(201);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 201 so currentWeather.getConditionDescriptionResId() = R.string.condition_201_description", currentWeather.getConditionDescriptionResId(), R.string.condition_201_description);

        Mockito.when(apiCondition.getId()).thenReturn(202);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 202 so currentWeather.getConditionDescriptionResId() = R.string.condition_202_description", currentWeather.getConditionDescriptionResId(), R.string.condition_202_description);

        Mockito.when(apiCondition.getId()).thenReturn(210);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 210 so currentWeather.getConditionDescriptionResId() = R.string.condition_210_description", currentWeather.getConditionDescriptionResId(), R.string.condition_210_description);

        Mockito.when(apiCondition.getId()).thenReturn(211);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 211 so currentWeather.getConditionDescriptionResId() = R.string.condition_211_description", currentWeather.getConditionDescriptionResId(), R.string.condition_211_description);

        Mockito.when(apiCondition.getId()).thenReturn(212);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 212 so currentWeather.getConditionDescriptionResId() = R.string.condition_212_description", currentWeather.getConditionDescriptionResId(), R.string.condition_212_description);

        Mockito.when(apiCondition.getId()).thenReturn(221);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 221 so currentWeather.getConditionDescriptionResId() = R.string.condition_221_description", currentWeather.getConditionDescriptionResId(), R.string.condition_221_description);

        Mockito.when(apiCondition.getId()).thenReturn(230);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 230 so currentWeather.getConditionDescriptionResId() = R.string.condition_230_description", currentWeather.getConditionDescriptionResId(), R.string.condition_230_description);

        Mockito.when(apiCondition.getId()).thenReturn(231);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 231 so currentWeather.getConditionDescriptionResId() = R.string.condition_231_description", currentWeather.getConditionDescriptionResId(), R.string.condition_231_description);

        Mockito.when(apiCondition.getId()).thenReturn(232);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 232 so currentWeather.getConditionDescriptionResId() = R.string.condition_232_description", currentWeather.getConditionDescriptionResId(), R.string.condition_232_description);


        Mockito.when(apiCondition.getId()).thenReturn(300);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 300 so currentWeather.getConditionDescriptionResId() = R.string.condition_300_description", currentWeather.getConditionDescriptionResId(), R.string.condition_300_description);

        Mockito.when(apiCondition.getId()).thenReturn(301);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 301 so currentWeather.getConditionDescriptionResId() = R.string.condition_301_description", currentWeather.getConditionDescriptionResId(), R.string.condition_301_description);

        Mockito.when(apiCondition.getId()).thenReturn(302);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 302 so currentWeather.getConditionDescriptionResId() = R.string.condition_302_description", currentWeather.getConditionDescriptionResId(), R.string.condition_302_description);

        Mockito.when(apiCondition.getId()).thenReturn(310);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 310 so currentWeather.getConditionDescriptionResId() = R.string.condition_310_description", currentWeather.getConditionDescriptionResId(), R.string.condition_310_description);

        Mockito.when(apiCondition.getId()).thenReturn(311);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 311 so currentWeather.getConditionDescriptionResId() = R.string.condition_311_description", currentWeather.getConditionDescriptionResId(), R.string.condition_311_description);

        Mockito.when(apiCondition.getId()).thenReturn(312);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 312 so currentWeather.getConditionDescriptionResId() = R.string.condition_312_description", currentWeather.getConditionDescriptionResId(), R.string.condition_312_description);

        Mockito.when(apiCondition.getId()).thenReturn(313);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 313 so currentWeather.getConditionDescriptionResId() = R.string.condition_313_description", currentWeather.getConditionDescriptionResId(), R.string.condition_313_description);

        Mockito.when(apiCondition.getId()).thenReturn(314);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 314 so currentWeather.getConditionDescriptionResId() = R.string.condition_314_description", currentWeather.getConditionDescriptionResId(), R.string.condition_314_description);

        Mockito.when(apiCondition.getId()).thenReturn(321);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 321 so currentWeather.getConditionDescriptionResId() = R.string.condition_321_description", currentWeather.getConditionDescriptionResId(), R.string.condition_321_description);


        Mockito.when(apiCondition.getId()).thenReturn(500);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 500 so currentWeather.getConditionDescriptionResId() = R.string.condition_500_description", currentWeather.getConditionDescriptionResId(), R.string.condition_500_description);

        Mockito.when(apiCondition.getId()).thenReturn(501);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 501 so currentWeather.getConditionDescriptionResId() = R.string.condition_501_description", currentWeather.getConditionDescriptionResId(), R.string.condition_501_description);

        Mockito.when(apiCondition.getId()).thenReturn(502);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 502 so currentWeather.getConditionDescriptionResId() = R.string.condition_502_description", currentWeather.getConditionDescriptionResId(), R.string.condition_502_description);

        Mockito.when(apiCondition.getId()).thenReturn(503);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 503 so currentWeather.getConditionDescriptionResId() = R.string.condition_503_description", currentWeather.getConditionDescriptionResId(), R.string.condition_503_description);

        Mockito.when(apiCondition.getId()).thenReturn(504);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 504 so currentWeather.getConditionDescriptionResId() = R.string.condition_504_description", currentWeather.getConditionDescriptionResId(), R.string.condition_504_description);

        Mockito.when(apiCondition.getId()).thenReturn(511);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 511 so currentWeather.getConditionDescriptionResId() = R.string.condition_511_description", currentWeather.getConditionDescriptionResId(), R.string.condition_511_description);

        Mockito.when(apiCondition.getId()).thenReturn(520);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 520 so currentWeather.getConditionDescriptionResId() = R.string.condition_520_description", currentWeather.getConditionDescriptionResId(), R.string.condition_520_description);

        Mockito.when(apiCondition.getId()).thenReturn(521);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 521 so currentWeather.getConditionDescriptionResId() = R.string.condition_521_description", currentWeather.getConditionDescriptionResId(), R.string.condition_521_description);

        Mockito.when(apiCondition.getId()).thenReturn(522);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 522 so currentWeather.getConditionDescriptionResId() = R.string.condition_522_description", currentWeather.getConditionDescriptionResId(), R.string.condition_522_description);

        Mockito.when(apiCondition.getId()).thenReturn(531);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 531 so currentWeather.getConditionDescriptionResId() = R.string.condition_531_description", currentWeather.getConditionDescriptionResId(), R.string.condition_531_description);


        Mockito.when(apiCondition.getId()).thenReturn(600);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 600 so currentWeather.getConditionDescriptionResId() = R.string.condition_600_description", currentWeather.getConditionDescriptionResId(), R.string.condition_600_description);

        Mockito.when(apiCondition.getId()).thenReturn(601);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 601 so currentWeather.getConditionDescriptionResId() = R.string.condition_601_description", currentWeather.getConditionDescriptionResId(), R.string.condition_601_description);

        Mockito.when(apiCondition.getId()).thenReturn(602);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 602 so currentWeather.getConditionDescriptionResId() = R.string.condition_602_description", currentWeather.getConditionDescriptionResId(), R.string.condition_602_description);

        Mockito.when(apiCondition.getId()).thenReturn(611);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 611 so currentWeather.getConditionDescriptionResId() = R.string.condition_611_description", currentWeather.getConditionDescriptionResId(), R.string.condition_611_description);

        Mockito.when(apiCondition.getId()).thenReturn(612);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 612 so currentWeather.getConditionDescriptionResId() = R.string.condition_612_description", currentWeather.getConditionDescriptionResId(), R.string.condition_612_description);

        Mockito.when(apiCondition.getId()).thenReturn(615);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 615 so currentWeather.getConditionDescriptionResId() = R.string.condition_615_description", currentWeather.getConditionDescriptionResId(), R.string.condition_615_description);

        Mockito.when(apiCondition.getId()).thenReturn(616);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 616 so currentWeather.getConditionDescriptionResId() = R.string.condition_616_description", currentWeather.getConditionDescriptionResId(), R.string.condition_616_description);

        Mockito.when(apiCondition.getId()).thenReturn(620);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 620 so currentWeather.getConditionDescriptionResId() = R.string.condition_620_description", currentWeather.getConditionDescriptionResId(), R.string.condition_620_description);

        Mockito.when(apiCondition.getId()).thenReturn(621);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 621 so currentWeather.getConditionDescriptionResId() = R.string.condition_621_description", currentWeather.getConditionDescriptionResId(), R.string.condition_621_description);

        Mockito.when(apiCondition.getId()).thenReturn(622);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 622 so currentWeather.getConditionDescriptionResId() = R.string.condition_622_description", currentWeather.getConditionDescriptionResId(), R.string.condition_622_description);


        Mockito.when(apiCondition.getId()).thenReturn(701);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 701 so currentWeather.getConditionDescriptionResId() = R.string.condition_701_description", currentWeather.getConditionDescriptionResId(), R.string.condition_701_description);

        Mockito.when(apiCondition.getId()).thenReturn(711);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 711 so currentWeather.getConditionDescriptionResId() = R.string.condition_711_description", currentWeather.getConditionDescriptionResId(), R.string.condition_711_description);

        Mockito.when(apiCondition.getId()).thenReturn(721);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 721 so currentWeather.getConditionDescriptionResId() = R.string.condition_721_description", currentWeather.getConditionDescriptionResId(), R.string.condition_721_description);

        Mockito.when(apiCondition.getId()).thenReturn(731);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 731 so currentWeather.getConditionDescriptionResId() = R.string.condition_731_description", currentWeather.getConditionDescriptionResId(), R.string.condition_731_description);

        Mockito.when(apiCondition.getId()).thenReturn(741);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 741 so currentWeather.getConditionDescriptionResId() = R.string.condition_741_description", currentWeather.getConditionDescriptionResId(), R.string.condition_741_description);

        Mockito.when(apiCondition.getId()).thenReturn(751);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 751 so currentWeather.getConditionDescriptionResId() = R.string.condition_751_description", currentWeather.getConditionDescriptionResId(), R.string.condition_751_description);

        Mockito.when(apiCondition.getId()).thenReturn(761);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 761 so currentWeather.getConditionDescriptionResId() = R.string.condition_761_description", currentWeather.getConditionDescriptionResId(), R.string.condition_761_description);

        Mockito.when(apiCondition.getId()).thenReturn(762);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 762 so currentWeather.getConditionDescriptionResId() = R.string.condition_762_description", currentWeather.getConditionDescriptionResId(), R.string.condition_762_description);

        Mockito.when(apiCondition.getId()).thenReturn(771);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 771 so currentWeather.getConditionDescriptionResId() = R.string.condition_771_description", currentWeather.getConditionDescriptionResId(), R.string.condition_771_description);

        Mockito.when(apiCondition.getId()).thenReturn(781);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 781 so currentWeather.getConditionDescriptionResId() = R.string.condition_781_description", currentWeather.getConditionDescriptionResId(), R.string.condition_781_description);


        Mockito.when(apiCondition.getId()).thenReturn(800);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 800 so currentWeather.getConditionDescriptionResId() = R.string.condition_800_description", currentWeather.getConditionDescriptionResId(), R.string.condition_800_description);

        Mockito.when(apiCondition.getId()).thenReturn(801);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 801 so currentWeather.getConditionDescriptionResId() = R.string.condition_801_description", currentWeather.getConditionDescriptionResId(), R.string.condition_801_description);

        Mockito.when(apiCondition.getId()).thenReturn(802);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 802 so currentWeather.getConditionDescriptionResId() = R.string.condition_802_description", currentWeather.getConditionDescriptionResId(), R.string.condition_802_description);

        Mockito.when(apiCondition.getId()).thenReturn(803);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 803 so currentWeather.getConditionDescriptionResId() = R.string.condition_803_description", currentWeather.getConditionDescriptionResId(), R.string.condition_803_description);

        Mockito.when(apiCondition.getId()).thenReturn(804);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 804 so currentWeather.getConditionDescriptionResId() = R.string.condition_804_description", currentWeather.getConditionDescriptionResId(), R.string.condition_804_description);


        Mockito.when(apiCondition.getId()).thenReturn(900);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 900 so currentWeather.getConditionDescriptionResId() = R.string.condition_900_description", currentWeather.getConditionDescriptionResId(), R.string.condition_900_description);

        Mockito.when(apiCondition.getId()).thenReturn(901);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 901 so currentWeather.getConditionDescriptionResId() = R.string.condition_901_description", currentWeather.getConditionDescriptionResId(), R.string.condition_901_description);

        Mockito.when(apiCondition.getId()).thenReturn(902);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 902 so currentWeather.getConditionDescriptionResId() = R.string.condition_902_description", currentWeather.getConditionDescriptionResId(), R.string.condition_902_description);

        Mockito.when(apiCondition.getId()).thenReturn(903);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 903 so currentWeather.getConditionDescriptionResId() = R.string.condition_903_description", currentWeather.getConditionDescriptionResId(), R.string.condition_903_description);

        Mockito.when(apiCondition.getId()).thenReturn(904);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 904 so currentWeather.getConditionDescriptionResId() = R.string.condition_904_description", currentWeather.getConditionDescriptionResId(), R.string.condition_904_description);

        Mockito.when(apiCondition.getId()).thenReturn(905);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 905 so currentWeather.getConditionDescriptionResId() = R.string.condition_905_description", currentWeather.getConditionDescriptionResId(), R.string.condition_905_description);

        Mockito.when(apiCondition.getId()).thenReturn(906);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 906 so currentWeather.getConditionDescriptionResId() = R.string.condition_906_description", currentWeather.getConditionDescriptionResId(), R.string.condition_906_description);


        Mockito.when(apiCondition.getId()).thenReturn(951);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 951 so currentWeather.getConditionDescriptionResId() = R.string.condition_951_description", currentWeather.getConditionDescriptionResId(), R.string.condition_951_description);

        Mockito.when(apiCondition.getId()).thenReturn(952);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 952 so currentWeather.getConditionDescriptionResId() = R.string.condition_952_description", currentWeather.getConditionDescriptionResId(), R.string.condition_952_description);

        Mockito.when(apiCondition.getId()).thenReturn(953);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 953 so currentWeather.getConditionDescriptionResId() = R.string.condition_953_description", currentWeather.getConditionDescriptionResId(), R.string.condition_953_description);

        Mockito.when(apiCondition.getId()).thenReturn(954);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 954 so currentWeather.getConditionDescriptionResId() = R.string.condition_954_description", currentWeather.getConditionDescriptionResId(), R.string.condition_954_description);

        Mockito.when(apiCondition.getId()).thenReturn(955);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 955 so currentWeather.getConditionDescriptionResId() = R.string.condition_955_description", currentWeather.getConditionDescriptionResId(), R.string.condition_955_description);

        Mockito.when(apiCondition.getId()).thenReturn(956);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 956 so currentWeather.getConditionDescriptionResId() = R.string.condition_956_description", currentWeather.getConditionDescriptionResId(), R.string.condition_956_description);

        Mockito.when(apiCondition.getId()).thenReturn(957);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 957 so currentWeather.getConditionDescriptionResId() = R.string.condition_957_description", currentWeather.getConditionDescriptionResId(), R.string.condition_957_description);

        Mockito.when(apiCondition.getId()).thenReturn(958);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 958 so currentWeather.getConditionDescriptionResId() = R.string.condition_958_description", currentWeather.getConditionDescriptionResId(), R.string.condition_958_description);

        Mockito.when(apiCondition.getId()).thenReturn(959);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 959 so currentWeather.getConditionDescriptionResId() = R.string.condition_959_description", currentWeather.getConditionDescriptionResId(), R.string.condition_959_description);

        Mockito.when(apiCondition.getId()).thenReturn(960);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 960 so currentWeather.getConditionDescriptionResId() = R.string.condition_960_description", currentWeather.getConditionDescriptionResId(), R.string.condition_960_description);

        Mockito.when(apiCondition.getId()).thenReturn(961);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 961 so currentWeather.getConditionDescriptionResId() = R.string.condition_961_description", currentWeather.getConditionDescriptionResId(), R.string.condition_961_description);

        Mockito.when(apiCondition.getId()).thenReturn(962);
        currentWeather = new CurrentWeather(apiWeather);
        assertEquals("apiCondition.getId() = 962 so currentWeather.getConditionDescriptionResId() = R.string.condition_962_description", currentWeather.getConditionDescriptionResId(), R.string.condition_962_description);
    }
}
