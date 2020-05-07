package com.cralos.cargamosmvvm.movies.fragments.favoritemovies.realm;

import com.cralos.cargamosmvvm.movies.fragments.mainmovies.models.Movie;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmQueries {
    public static List<Movie> getAllMovies() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Movie> realmResults = realm.where(Movie.class).findAll();
        if (realmResults != null && realmResults.size() > 0) {
            List<Movie> movies = realm.copyFromRealm(realmResults);
            realm.close();
            return movies;
        } else {
            realm.close();
            return null;
        }
    }
}
