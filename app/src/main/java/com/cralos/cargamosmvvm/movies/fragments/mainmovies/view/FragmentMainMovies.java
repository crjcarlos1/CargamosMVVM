package com.cralos.cargamosmvvm.movies.fragments.mainmovies.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.cralos.cargamosmvvm.R;
import com.cralos.cargamosmvvm.databinding.FragmentMainMoviesBinding;

public class FragmentMainMovies extends Fragment {
    public static final String TAG = FragmentMainMovies.class.getSimpleName();

    /**
     * binding
     */
    private FragmentMainMoviesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_movies, container, false);
        initFragmentMainMovies();
        return binding.getRoot();
    }

    private void initFragmentMainMovies(){

    }

}
