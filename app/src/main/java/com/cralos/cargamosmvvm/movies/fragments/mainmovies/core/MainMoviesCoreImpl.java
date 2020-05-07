package com.cralos.cargamosmvvm.movies.fragments.mainmovies.core;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cralos.cargamosmvvm.R;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces.MainMoviesCore;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces.MainMoviesServices;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces.MainMoviesViewModel;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.MainMoviesResponse;
import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;
import com.cralos.cargamosmvvm.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainMoviesCoreImpl implements MainMoviesCore {

    private MainMoviesViewModel viewModel;
    private Context context;
    private Retrofit retrofit;
    private MainMoviesServices services;

    public MainMoviesCoreImpl(MainMoviesViewModel viewModel, Context context) {
        this.viewModel = viewModel;
        this.context = context;
        this.retrofit = RetrofitClient.getInstance();
        this.services = retrofit.create(MainMoviesServices.class);
    }

    @Override
    public void getMovies(int currentPage) {
        Call<MainMoviesResponse> call = services.getMainMovies(context.getString(R.string.keyMovies), currentPage);
        call.enqueue(new Callback<MainMoviesResponse>() {
            @Override
            public void onResponse(Call<MainMoviesResponse> call, Response<MainMoviesResponse> response) {
                validateResponse(response);
            }

            @Override
            public void onFailure(Call<MainMoviesResponse> call, Throwable t) {
                Log.e("MOVIES", "ERROR: " + t.getMessage());
            }
        });
    }

    private void validateResponse(Response<MainMoviesResponse> response) {
        if (response != null) {
            validateResponseCode(response);
        } else {
            Log.e("MOVIES", "ERROR: no se encontraron datos (1) ");
        }
    }

    private void validateResponseCode(Response<MainMoviesResponse> response) {
        if (response.code() == 200) {
            validateMainMoviesResponse(response);
        } else {
            Log.e("MOVIES", "ERROR response code: " + response.code());
        }
    }

    private void validateMainMoviesResponse(Response<MainMoviesResponse> response) {
        MainMoviesResponse mainMoviesResponse = response.body();
        if (mainMoviesResponse != null) {
            validateMoviesList(mainMoviesResponse);
        } else {
            Log.e("MOVIES", "ERROR no se encontraron datos (2)");
        }
    }

    private void validateMoviesList(MainMoviesResponse mainMoviesResponse) {
        List<Movie> movies = mainMoviesResponse.getResults();
        if (movies != null && movies.size() > 0) {
            MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
            mutableLiveData.setValue(movies);
            viewModel.setLastPage(mainMoviesResponse.getTotalPages());
            viewModel.setMainMovies(mutableLiveData);
        } else {
            Log.e("MOVIES", "ERROR, SIN DATOS (3)");
        }
    }

}
