package net.gahfy.chilindoweather.utils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class Schedulers {
    public static Scheduler io(){
        return io.reactivex.schedulers.Schedulers.io();
    }

    public static Scheduler androidThread(){
        return AndroidSchedulers.mainThread();
    }
}
