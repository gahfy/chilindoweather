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
        OpenWeatherMapApi openWeatherMapApi;

        BasePresenterClass(@NonNull BaseViewClass view) {
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
        public void setTitle(int titleResId, String... formatString) {
        }

        @Override
        public void setTitle(CharSequence title) {
        }

        @Override
        public void startActivity(Class<? extends BaseActivity> activityClass) {
        }

        @Override
        public void startActivityForResult(Intent intent, int requestCode) {
        }

        @Override
        public void finish() {
        }
    }
}
