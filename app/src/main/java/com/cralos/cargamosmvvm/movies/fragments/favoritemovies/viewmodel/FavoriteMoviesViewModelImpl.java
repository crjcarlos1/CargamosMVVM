package com.cralos.cargamosmvvm.movies.fragments.favoritemovies.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.core.FavoriteMoviesCoreImpl;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.interfaces.FavoriteMoviesCore;
import com.cralos.cargamosmvvm.movies.fragments.favoritemovies.interfaces.FavoriteMoviesViewModel;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMoviesViewModelImpl extends ViewModel implements FavoriteMoviesViewModel {

    private FavoriteMoviesCore core;
    private Context context;

    private MutableLiveData<List<Movie>> movies;
    private MutableLiveData<String> message;

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void initFavoriteMoviesViewModel(Context context) {
        this.context = context;
        this.movies = new MutableLiveData<>();
        this.message = new MutableLiveData<>();
        this.movies.setValue(new ArrayList<Movie>());
        this.core = new FavoriteMoviesCoreImpl(this, context);
    }

    public void getMoviesFromCore() {
        core.getAllMovies();
    }

    @Override
    public void setMovies(List<Movie> movieList) {
        movies.setValue(movieList);
    }

    @Override
    public void setMessage(String message) {
        this.message.setValue(message);
    }
}
