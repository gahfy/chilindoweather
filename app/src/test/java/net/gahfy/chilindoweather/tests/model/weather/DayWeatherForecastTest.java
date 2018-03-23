package net.gahfy.chilindoweather.tests.model.weather;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import net.gahfy.chilindoweather.model.api.ApiForecast;
import net.gahfy.chilindoweather.model.weather.DayWeatherForecast;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

public class DayWeatherForecastTest {
    private static final String JSON_MINIMAL = "{\"cod\": \"200\"," +
            "\"message\": 0.0048," +
            "\"cnt\": 40," +
            "\"list\": []," +
            "\"city\": {}" +
            "}";

    private static final String JSON_TWO_SAME_DAY = "{" +
            "\"cod\": \"200\"," +
            "\"message\": 0.0058," +
            "\"cnt\": 40," +
            "\"list\": [" +
            "{" +
            "\"dt\": 1521828000," +
            "\"main\": {" +
            "\"temp\": 281.69," +
            "\"temp_min\": 281.69," +
            "\"temp_max\": 284.026," +
            "\"pressure\": 1022.67," +
            "\"sea_level\": 1032.26," +
            "\"grnd_level\": 1022.67," +
            "\"humidity\": 100," +
            "\"temp_kf\": -2.33" +
            "}," +
            "\"weather\": [" +
            "{" +
            "\"id\": 500," +
            "\"main\": \"Rain\"," +
            "\"description\": \"light rain\"," +
            "\"icon\": \"10n\"" +
            "}" +
            "]," +
            "\"clouds\": {" +
            "\"all\": 56" +
            "}," +
            "\"wind\": {" +
            "\"speed\": 5.26," +
            "\"deg\": 25.5021" +
            "}," +
            "\"rain\": {" +
            "\"3h\": 0.65" +
            "}," +
            "\"sys\": {" +
            "\"pod\": \"n\"" +
            "}," +
            "\"dt_txt\": \"2018-03-23 18:00:00\"" +
            "}," +
            "{" +
            "\"dt\": 1521817200," +
            "\"main\": {" +
            "\"temp\": 281.73," +
            "\"temp_min\": 281.73," +
            "\"temp_max\": 284.84," +
            "\"pressure\": 1022.25," +
            "\"sea_level\": 1031.68," +
            "\"grnd_level\": 1022.25," +
            "\"humidity\": 100," +
            "\"temp_kf\": -3.11" +
            "}," +
            "\"weather\": [" +
            "{" +
            "\"id\": 500," +
            "\"main\": \"Rain\"," +
            "\"description\": \"light rain\"," +
            "\"icon\": \"10n\"" +
            "}" +
            "]," +
            "\"clouds\": {" +
            "\"all\": 92" +
            "}," +
            "\"wind\": {" +
            "\"speed\": 4.46," +
            "\"deg\": 22.5006" +
            "}," +
            "\"rain\": {" +
            "\"3h\": 0.78" +
            "}," +
            "\"sys\": {" +
            "\"pod\": \"n\"" +
            "}," +
            "\"dt_txt\": \"2018-03-23 15:00:00\"" +
            "}" +
            "]," +
            "\"city\": {" +
            "\"id\": 1851632," +
            "\"name\": \"Shuzenji\"," +
            "\"coord\": {" +
            "\"lat\": 34.9667," +
            "\"lon\": 138.9333" +
            "}," +
            "\"country\": \"JP\"" +
            "}" +
            "}";

    private final Moshi moshi = new Moshi.Builder().build();
    private final JsonAdapter<ApiForecast> jsonAdapter = moshi.adapter(ApiForecast.class);

    @Test
    public void testGetDayWeatherForecastList() throws Exception {
        ApiForecast apiForecastMinimal = jsonAdapter.fromJson(JSON_MINIMAL);

        List<DayWeatherForecast> dayWeatherForecastListMinimal = DayWeatherForecast.getDayWeatherForecastList(apiForecastMinimal);
        assertNotNull("List of day weather forecast", dayWeatherForecastListMinimal);
        assertEquals("Size of the list", 0, dayWeatherForecastListMinimal.size());


        ApiForecast apiForecastTwoSameDay = jsonAdapter.fromJson(JSON_TWO_SAME_DAY);

        List<DayWeatherForecast> dayWeatherForecastListTwoSameDay = DayWeatherForecast.getDayWeatherForecastList(apiForecastTwoSameDay);
        assertNotNull("List of day weather forecast", dayWeatherForecastListTwoSameDay);
        assertEquals("Size of the list", 1, dayWeatherForecastListTwoSameDay.size());
    }
}
