package net.gahfy.chilindoweather;

import android.app.Application;
import android.content.Intent;

import com.squareup.leakcanary.LeakCanary;

import net.gahfy.chilindoweather.ui.base.BaseActivity;

import java.util.ArrayList;

public class ChilindoWeatherApplication extends Application {
    public ArrayList<BaseActivity> activityStack = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    public void addActivity(BaseActivity baseActivity) {
        activityStack.add(baseActivity);
    }

    public void removeActivity(BaseActivity baseActivity) {
        activityStack.remove(baseActivity);
    }

    public void startActivity(BaseActivity originActivity, Class<? extends BaseActivity> activityClass) {
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
