package net.gahfy.chilindoweather.utils;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public final class Schedulers {
    private Schedulers() {
    }

    @NonNull
    public static Scheduler io(){
        return io.reactivex.schedulers.Schedulers.io();
    }

    @NonNull
    public static Scheduler androidThread(){
        return AndroidSchedulers.mainThread();
    }
}
