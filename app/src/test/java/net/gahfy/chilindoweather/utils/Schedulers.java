package net.gahfy.chilindoweather.utils;


import io.reactivex.Scheduler;

// Safe as it is used outside package only in main app
@SuppressWarnings("WeakerAccess")
public class Schedulers {
    // Safe because it is used in app code but not in tests
    @SuppressWarnings("UnusedDeclaration")
    public static Scheduler io(){
        return io.reactivex.schedulers.Schedulers.trampoline();
    }

    // Safe because it is used in app code but not in tests
    @SuppressWarnings("UnusedDeclaration")
    public static Scheduler androidThread(){
        return io.reactivex.schedulers.Schedulers.trampoline();
    }
}
