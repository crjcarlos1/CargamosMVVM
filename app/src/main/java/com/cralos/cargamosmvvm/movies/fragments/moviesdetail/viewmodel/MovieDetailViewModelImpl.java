package com.cralos.cargamosmvvm.movies.fragments.moviesdetail.viewmodel;

import android.content.Context;
import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.core.MovieDetailCoreImpl;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.interfaces.MovieDetailCore;
import com.cralos.cargamosmvvm.movies.fragments.moviesdetail.interfaces.MovieDetailViewModel;

public class MovieDetailViewModelImpl extends ViewModel implements MovieDetailViewModel {

    private Context context;
    private MovieDetailCore core;

    private MutableLiveData<Movie> movie;
    private MutableLiveData<String> message;
    private MutableLiveData<Boolean> isFavoriteMovie;

    public LiveData<Movie> getMovie() {
        return movie;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public LiveData<Boolean> getIsFavoriteMovie() {
        return isFavoriteMovie;
    }

    public void initMovieDetailViewModel(Context context) {
        this.context = context;
        this.movie = new MutableLiveData<>();
        this.message = new MutableLiveData<>();
        this.isFavoriteMovie = new MutableLiveData<>();
        this.core = new MovieDetailCoreImpl(context, this);
    }

    public void getMovieFromCore(Bundle bundle) {
        core.getMovie(bundle);
    }

    public void saveMovie(Movie movie) {
        core.saveMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        core.deleteMovie(movie);
    }

    @Override
    public void setMessage(String message) {
        this.message.setValue(message);
    }

    @Override
    public void setMovie(Movie movie) {
        this.movie.setValue(movie);
    }

    @Override
    public void existMovie(boolean existMovie) {
        isFavoriteMovie.setValue(existMovie);
    }
}
