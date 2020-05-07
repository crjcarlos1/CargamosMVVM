package com.cralos.cargamosmvvm.movies.fragments.mainmovies.interfaces;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.MainMoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainMoviesServices {
    @GET("movie")
    Call<MainMoviesResponse> getMainMovies(@Query("api_key") String key,@Query("page") int page);
}
