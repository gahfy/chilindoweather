package net.gahfy.chilindoweather;

import android.app.Application;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.squareup.leakcanary.LeakCanary;

import net.gahfy.chilindoweather.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public final class ChilindoWeatherApplication extends Application {
    @NonNull
    private final List<BaseActivity> activityStack = new ArrayList<>();

    @Override
    public final void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public final void addActivity(@NonNull final BaseActivity baseActivity) {
        activityStack.add(baseActivity);
    }

    public final void removeActivity(@NonNull final BaseActivity baseActivity) {
        activityStack.remove(baseActivity);
    }

    public final void startActivity(@NonNull final BaseActivity originActivity, @NonNull final Class<? extends BaseActivity> activityClass) {
        int indexFound = -1;
        for (int i = 0; i < activityStack.size(); i++) {
            if (activityClass.isInstance(activityStack.get(i))) {
                indexFound = i;
            }
        }
        if (indexFound > -1) {
            for (int i = indexFound + 1; i < activityStack.size(); i++) {
                activityStack.get(i).finish();
            }
        } else {
            Intent intent = new Intent(originActivity, activityClass);
            startActivity(intent);
        }
    }
}
