package net.gahfy.chilindoweather.utils.databinding;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class DataBindingAdapters {
    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        if (resource != 0) {
            Glide.with(imageView).load(ContextCompat.getDrawable(imageView.getContext(), resource)).into(imageView);
        }
    }

    @BindingAdapter("android:contentDescription")
    public static void setContentDescription(ImageView imageView, int resource) {
        imageView.setContentDescription(resource == 0 ? "" : imageView.getContext().getString(resource));
    }
}
