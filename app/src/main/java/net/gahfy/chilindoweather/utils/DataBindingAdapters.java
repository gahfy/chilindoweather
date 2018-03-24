package net.gahfy.chilindoweather.utils;

import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

// Safe as this class is used in generated code
@SuppressWarnings("WeakerAccess")
public final class DataBindingAdapters {
    private DataBindingAdapters() {
    }

    @BindingAdapter("android:src")
    public static void setImageResource(@NonNull final ImageView imageView, final int resource) {
        if (resource != 0) {
            Glide.with(imageView).load(ContextCompat.getDrawable(imageView.getContext(), resource)).into(imageView);
        }
    }

    @BindingAdapter("android:contentDescription")
    public static void setContentDescription(@NonNull final ImageView imageView, final int resource) {
        imageView.setContentDescription(resource == 0 ? "" : imageView.getContext().getString(resource));
    }
}
