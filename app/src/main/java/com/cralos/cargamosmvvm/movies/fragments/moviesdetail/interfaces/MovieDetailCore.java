package com.cralos.cargamosmvvm.movies.fragments.moviesdetail.interfaces;

import android.os.Bundle;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

public interface MovieDetailCore {
    void getMovie(Bundle bundle);

    void saveMovie(Movie movie);

    void deleteMovie(Movie movie);
}
