package com.cralos.cargamosmvvm.loader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.cralos.cargamosmvvm.R;

public class LoaderMovies extends DialogFragment {
    public static final String TAG = LoaderMovies.class.getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loader, container, false);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.colorTransparent);
        setCancelable(false);
        return view;
    }
}
