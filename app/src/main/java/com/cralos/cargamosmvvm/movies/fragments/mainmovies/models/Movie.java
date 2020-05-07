package com.cralos.cargamosmvvm.movies.fragments.mainmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Movie extends RealmObject implements Parcelable {

    @PrimaryKey
    private int id;
    private float popularity;
    private boolean video;
    private int vote_count;
    private float vote_average;
    private String title;
    private String release_date;
    private String original_language;
    private String original_title;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String poster_path;

    /**
     * No args constructor for use in serialization
     */
    public Movie() {
    }

    /**
     * @param overview
     * @param voteAverage
     * @param releaseDate
     * @param video
     * @param title
     * @param originalLanguage
     * @param originalTitle
     * @param popularity
     * @param id
     * @param voteCount
     * @param backdropPath
     * @param adult
     * @param posterPath
     */
    public Movie(float popularity, int id, boolean video, int voteCount, float voteAverage, String title, String releaseDate, String originalLanguage, String originalTitle, String backdropPath, boolean adult, String overview, String posterPath) {
        super();
        this.popularity = popularity;
        this.id = id;
        this.video = video;
        this.vote_count = voteCount;
        this.vote_average = voteAverage;
        this.title = title;
        this.release_date = releaseDate;
        this.original_language = originalLanguage;
        this.original_title = originalTitle;
        this.backdrop_path = backdropPath;
        this.adult = adult;
        this.overview = overview;
        this.poster_path = posterPath;
    }

    protected Movie(Parcel in) {
        popularity = in.readFloat();
        id = in.readInt();
        video = in.readByte() != 0;
        vote_count = in.readInt();
        vote_average = in.readFloat();
        title = in.readString();
        release_date = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        backdrop_path = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        poster_path = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getVoteCount() {
        return vote_count;
    }

    public void setVoteCount(int voteCount) {
        this.vote_count = voteCount;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(float voteAverage) {
        this.vote_average = voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return release_date;
    }

    public void setReleaseDate(String releaseDate) {
        this.release_date = releaseDate;
    }

    public String getOriginalLanguage() {
        return original_language;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.original_language = originalLanguage;
    }

    public String getOriginalTitle() {
        return original_title;
    }

    public void setOriginalTitle(String originalTitle) {
        this.original_title = originalTitle;
    }

    public String getBackdropPath() {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdrop_path = backdropPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String posterPath) {
        this.poster_path = posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(popularity);
        parcel.writeInt(id);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeInt(vote_count);
        parcel.writeFloat(vote_average);
        parcel.writeString(title);
        parcel.writeString(release_date);
        parcel.writeString(original_language);
        parcel.writeString(original_title);
        parcel.writeString(backdrop_path);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(overview);
        parcel.writeString(poster_path);
    }
}
