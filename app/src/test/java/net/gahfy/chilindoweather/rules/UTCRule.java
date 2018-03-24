package net.gahfy.chilindoweather.rules;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.TimeZone;

public class UTCRule extends TestWatcher {

    private final TimeZone origDefault = TimeZone.getDefault();

    @Override
    protected void starting(Description description) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @Override
    protected void finished(Description description) {
        TimeZone.setDefault(origDefault);
    }
}
