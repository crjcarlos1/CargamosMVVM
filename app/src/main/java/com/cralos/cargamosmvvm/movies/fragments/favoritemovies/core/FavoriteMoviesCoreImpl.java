package com.cralos.cargamosmvvm.movies.fragments.favoritemovies.core;

import android.content.Context;

import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.interfaces.FavoriteMoviesCore;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.interfaces.FavoriteMoviesViewModel;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.realm.RealmQueries;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import java.util.List;

public class FavoriteMoviesCoreImpl implements FavoriteMoviesCore {

    private FavoriteMoviesViewModel viewModel;
    private Context context;

    public FavoriteMoviesCoreImpl(FavoriteMoviesViewModel viewModel, Context context) {
        this.viewModel = viewModel;
        this.context = context;
    }

    @Override
    public void getAllMovies() {
        List<Movie> movies = RealmQueries.getAllMovies();
        if (movies != null && movies.size() > 0) {
            viewModel.setMovies(movies);
        } else {
            viewModel.setMessage("No se encontraron películas");
        }
    }
}
