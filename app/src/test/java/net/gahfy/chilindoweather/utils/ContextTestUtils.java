package net.gahfy.chilindoweather.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;

import net.gahfy.chilindoweather.R;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

public class ContextTestUtils {
    private static Context context = null;

    public static Context getContext() {
        if (context == null) {
            context = instantiateContext();
        }
        return context;
    }

    private static Context instantiateContext() {
        LocaleList localeList = Mockito.mock(LocaleList.class);
        Mockito.when(localeList.get(0)).thenReturn(Locale.US);

        Context context = Mockito.mock(Context.class);
        Resources resources = Mockito.mock(Resources.class);

        Configuration configuration = Mockito.mock(Configuration.class);
        Mockito.when(configuration.getLocales()).thenReturn(localeList);
        configuration.locale = localeList.get(0);

        Mockito.when(resources.getConfiguration()).thenReturn(configuration);
        Mockito.when(resources.getStringArray(R.array.week_days)).thenReturn(new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        Mockito.when(resources.getStringArray(R.array.months)).thenReturn(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});

        Mockito.when(context.getString(R.string.date_format_forecast)).thenReturn("{wd}, {mn} d, yyyy");
        Mockito.when(context.getString(R.string.calculation_date_format)).thenReturn("MM-dd-yyyy hh:mm a");
        Mockito.when(context.getString(R.string.empty)).thenReturn("");
        Mockito.when(context.getResources()).thenReturn(resources);

        Mockito.when(context.getString(R.string.unknown_temperature)).thenReturn("…°");
        Mockito.when(context.getString(eq(R.string.temperature_value), anyInt())).thenAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) throws Throwable {
                int argument = invocation.getArgument(1);
                return String.format(Locale.US, "%1$d°", argument);
            }
        });

        Mockito.when(context.getString(R.string.unknown_wind_speed)).thenReturn("… m/s");
        Mockito.when(context.getString(eq(R.string.wind_speed_metric), anyInt())).thenAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) throws Throwable {
                int argument = invocation.getArgument(1);
                return String.format(Locale.US, "%1$d m/s", argument);
            }
        });
        Mockito.when(context.getString(eq(R.string.wind_speed_imperial), anyInt())).thenAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) throws Throwable {
                int argument = invocation.getArgument(1);
                return String.format(Locale.US, "%1$d mph", argument);
            }
        });


        Mockito.when(context.getString(R.string.unknown_wind_direction)).thenReturn("…");
        Mockito.when(context.getString(R.string.north_abbr)).thenReturn("N");
        Mockito.when(context.getString(R.string.south_abbr)).thenReturn("S");
        Mockito.when(context.getString(R.string.east_abbr)).thenReturn("E");
        Mockito.when(context.getString(R.string.west_abbr)).thenReturn("W");
        Mockito.when(context.getString(eq(R.string.wind_direction_value), anyInt(), anyString())).thenAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) throws Throwable {
                int argument1 = invocation.getArgument(1);
                String argument2 = invocation.getArgument(2);
                return String.format(Locale.US, "%1$d°%2$s", argument1, argument2);
            }
        });

        Mockito.when(context.getString(R.string.unknown_pressure)).thenReturn("…hPa");
        Mockito.when(context.getString(eq(R.string.pressure_value), anyInt())).thenAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) throws Throwable {
                int argument = invocation.getArgument(1);
                return String.format(Locale.US, "%dhPa", argument);
            }
        });

        Mockito.when(context.getString(R.string.unknown_humidity)).thenReturn("… %");
        Mockito.when(context.getString(eq(R.string.humidity_value), anyInt())).thenAnswer(new Answer<String>() {
            public String answer(InvocationOnMock invocation) throws Throwable {
                int argument = invocation.getArgument(1);
                return String.format(Locale.US, "%d %%", argument);
            }
        });

        Mockito.when(context.getString(R.string.forecast_time_format)).thenReturn("hh a");

        return context;
    }
}
