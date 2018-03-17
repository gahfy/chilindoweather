package net.gahfy.chilindoweather.utils.databinding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class DataBindingAdapters {
    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("android:contentDescription")
    public static void setContentDescription(ImageView imageView, int resource) {
        imageView.setContentDescription(resource == 0 ? "" : imageView.getContext().getString(resource));
    }
}
