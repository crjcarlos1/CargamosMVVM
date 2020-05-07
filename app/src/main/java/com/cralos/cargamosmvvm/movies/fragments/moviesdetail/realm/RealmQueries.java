package com.cralos.cargamosmvvm.movies.fragments.moviesdetail.realm;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import io.realm.Realm;

public class RealmQueries {

    public static boolean existMovie(int movieId) {
        Realm realm = Realm.getDefaultInstance();
        Movie realmMovie = realm.where(Movie.class).equalTo("id", movieId).findFirst();
        if (realmMovie != null) {
            realm.close();
            return true;
        } else {
            realm.close();
            return false;
        }
    }

    public static boolean saveMovie(Movie movie) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            Movie movieRealm = realm.createObject(Movie.class, movie.getId());
            movieRealm.setOriginalTitle(movie.getOriginalTitle());
            movieRealm.setOverview(movie.getOverview());
            movieRealm.setVoteAverage(movie.getVoteAverage());
            movieRealm.setPosterPath(movie.getPosterPath());
            movieRealm.setTitle(movie.getTitle());
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            realm.commitTransaction();
            realm.close();
        }
    }

    public static boolean deleteMovie(int movieId) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            Movie realmMovie = realm.where(Movie.class).equalTo("id", movieId).findFirst();
            if (realmMovie != null) {
                realmMovie.deleteFromRealm();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        } finally {
            realm.commitTransaction();
            realm.close();
        }
    }

}
