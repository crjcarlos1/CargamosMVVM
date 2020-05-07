package com.cralos.cargamosmvvm.movies.fragments.favoritemovies.interfaces;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import java.util.List;

public interface FavoriteMoviesViewModel {
    void setMovies(List<Movie> movieList);

    void setMessage(String message);
}
