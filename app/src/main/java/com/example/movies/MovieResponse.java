package com.example.movies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    // list of movies we get as
    // response from Kinopoisk API
    @SerializedName("docs")
    private List<Movie> movies;

    public MovieResponse(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MoviesResponse{" +
                "movies=" + movies +
                '}';
    }
}
