package com.cralos.cargamosmvvm.movies.fragments.mainmovies.models;

import java.util.List;

public class MainMoviesResponse {
    private int page;
    private int total_results;
    private int total_pages;
    private List<Movie> results = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public MainMoviesResponse() {
    }

    /**
     *
     * @param totalResults
     * @param totalPages
     * @param page
     * @param results
     */
    public MainMoviesResponse(int page, int totalResults, int totalPages, List<Movie> results) {
        super();
        this.page = page;
        this.total_results = totalResults;
        this.total_pages = totalPages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return total_results;
    }

    public void setTotalResults(int totalResults) {
        this.total_results = totalResults;
    }

    public int getTotalPages() {
        return total_pages;
    }

    public void setTotalPages(int totalPages) {
        this.total_pages = totalPages;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
