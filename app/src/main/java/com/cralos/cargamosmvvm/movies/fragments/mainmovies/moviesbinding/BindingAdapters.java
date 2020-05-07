package com.cralos.cargamosmvvm.movies.fragments.mainmovies.moviesbinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapters {

    @BindingAdapter("imageUrlMovie")
    public static void setImage(ImageView imageView, String path) {
        String basePath = "http://image.tmdb.org/t/p/w780" + path;
        Glide.with(imageView.getContext())
                .load(basePath)
                .into(imageView);
    }

}
