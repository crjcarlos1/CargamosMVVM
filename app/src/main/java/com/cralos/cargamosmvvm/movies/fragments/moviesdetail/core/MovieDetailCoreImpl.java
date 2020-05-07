package com.cralos.cargamosmvvm.movies.fragments.moviesdetail.core;

import android.content.Context;
import android.os.Bundle;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.interfaces.MovieDetailCore;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.interfaces.MovieDetailViewModel;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.realm.RealmQueries;

public class MovieDetailCoreImpl implements MovieDetailCore {

    private Context context;
    private MovieDetailViewModel viewModel;

    public MovieDetailCoreImpl(Context context, MovieDetailViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override
    public void getMovie(Bundle bundle) {
        if (bundle != null) {
            Movie movie = bundle.getParcelable("MOVIE");
            viewModel.setMovie(movie);
            isFavoriteMovie(movie);
        } else {
            viewModel.setMessage("Error al obtener película");
        }
    }

    private void isFavoriteMovie(Movie movie) {
        if (movie != null) {
            viewModel.existMovie(RealmQueries.existMovie(movie.getId()));
        } else {
            viewModel.setMessage("Error al encontrar película (2)");
        }
    }

    @Override
    public void saveMovie(Movie movie) {
        if (movie != null) {
            viewModel.existMovie(RealmQueries.saveMovie(movie));
        } else {
            viewModel.setMessage("Error al obtener película (0)");
        }
    }

    @Override
    public void deleteMovie(Movie movie) {
        if (movie != null) {
            viewModel.existMovie(!RealmQueries.deleteMovie(movie.getId()));
        } else {
            viewModel.setMessage("Error al obtener película (1)");
        }
    }

}
