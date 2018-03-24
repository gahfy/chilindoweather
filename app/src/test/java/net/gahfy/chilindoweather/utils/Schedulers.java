package net.gahfy.chilindoweather.utils;


import io.reactivex.Scheduler;

public class Schedulers {
    public static Scheduler io(){
        return io.reactivex.schedulers.Schedulers.trampoline();
    }

    public static Scheduler androidThread(){
        return io.reactivex.schedulers.Schedulers.trampoline();
    }
}
