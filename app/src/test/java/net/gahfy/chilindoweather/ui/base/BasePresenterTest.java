package net.gahfy.chilindoweather.ui.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import net.gahfy.chilindoweather.network.OpenWeatherMapApi;
import net.gahfy.chilindoweather.utils.ContextTestUtils;

import org.junit.Test;

import javax.inject.Inject;

import static junit.framework.Assert.assertNull;

public class BasePresenterTest {
    @Test
    public void testConstructor() {
        BasePresenterClass basePresenterClass = new BasePresenterClass(new BaseViewClass());
        // Checks that nothing has been injected
        assertNull("basePresenterClass openWeatherMapApi", basePresenterClass.openWeatherMapApi);
    }

    private static class BasePresenterClass extends BasePresenter<BaseViewClass> {
        @Inject
        // Safe as we use it to check it is null
        @SuppressWarnings("unused")
        OpenWeatherMapApi openWeatherMapApi;

        BasePresenterClass(@NonNull final BaseViewClass view) {
            super(view);
        }
    }

    private static class BaseViewClass implements BaseView {

        @NonNull
        @Override
        public Context getContext() {
            return ContextTestUtils.getContext();
        }

        @Override
        public void setTitle(final int titleResId, @NonNull final String... formatString) {
        }

        @Override
        public void startActivity(@NonNull final Class<? extends BaseActivity> activityClass) {
        }

        @Override
        public void startActivityForResult(@NonNull final Intent intent, final int requestCode) {
        }

        @Override
        public void finish() {
        }
    }
}
