package net.gahfy.chilindoweather.utils;

// Safe as it is used outside package only in main app
// Safe as it is used in app code
@SuppressWarnings({"WeakerAccess", "UnusedDeclaration"})
public class HandlerUtils {
    // Safe as it used in app code
    public static void postDelayed(Runnable runnable, @SuppressWarnings("unused") int millisecondsDelay) {
        runnable.run();
    }
}
