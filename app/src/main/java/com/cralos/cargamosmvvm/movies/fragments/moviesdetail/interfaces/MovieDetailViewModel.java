package com.cralos.cargamosmvvm.movies.fragments.moviesdetail.interfaces;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

public interface MovieDetailViewModel {
    void setMessage(String message);

    void setMovie(Movie movie);

    void existMovie(boolean existMovie);
}
