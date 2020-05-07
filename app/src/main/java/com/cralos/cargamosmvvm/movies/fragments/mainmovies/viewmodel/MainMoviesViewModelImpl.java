package com.cralos.cargamosmvvm.movies.fragments.mainmovies.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.core.MainMoviesCoreImpl;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces.MainMoviesCore;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces.MainMoviesViewModel;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainMoviesViewModelImpl extends ViewModel implements MainMoviesViewModel {

    private MainMoviesCore core;
    private int lastPage = 10;
    private int currentPage = 1;

    private MutableLiveData<List<Movie>> movies;
    private MutableLiveData<Boolean> isUpdating;
    private MutableLiveData<String> message;

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public MutableLiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public LiveData<String> getMessage() {
        return message;
    }

    public void initMainMoviesViewModel(Context context) {
        if (movies == null) {
            movies = new MutableLiveData<>();
            isUpdating = new MutableLiveData<>();
            message = new MutableLiveData<>();
            core = new MainMoviesCoreImpl(this, context);
            movies.setValue(new ArrayList<Movie>());/**sgregamos  lista para que sea distinto de nuul y no crashee*/
        }
    }

    public void getMoviesFromApi() {
        if (currentPage <= lastPage) {
            isUpdating.setValue(true);
            core.getMovies(currentPage);
        }
    }

    @Override
    public void setMainMovies(MutableLiveData<List<Movie>> newMovies) {
        isUpdating.setValue(false);
        List<Movie> currentMovies = movies.getValue();
        currentMovies.addAll(newMovies.getValue());
        movies.setValue(currentMovies);
    }

    @Override
    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
        currentPage++;
    }

    @Override
    public void setMessage(String message) {
        this.message.setValue(message);
        isUpdating.setValue(false);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
