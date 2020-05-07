package com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces;

import androidx.lifecycle.MutableLiveData;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import java.util.List;

public interface MainMoviesViewModel {
    void setMainMovies(MutableLiveData<List<Movie>> movies);
    void setLastPage(int lastPage);
    void setMessage(String message);
}
