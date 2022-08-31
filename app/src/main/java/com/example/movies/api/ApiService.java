package com.example.movies.api;

import com.example.movies.data.response.MovieResponse;
import com.example.movies.data.response.PersonResponse;
import com.example.movies.data.response.ReviewResponse;
import com.example.movies.data.response.TrailerResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

// interface with Retrofit request
public interface ApiService {

    @GET("movie?token=RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MovieResponse> loadMovies(
            @Query("page") int page
    );

    @GET("movie?token=RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J&field=id")
    Single<PersonResponse> loadPersons(
            @Query("search") long movieId
    );

    @GET("movie?token=RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J&field=id")
    Single<TrailerResponse> loadTrailers(
            @Query("search") long movieId
    );

    @GET("review?token=RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J&sortField=date&sortType=-1&limit=10&field=movieId")
    Single<ReviewResponse> loadReviews(
            @Query("search") long movieId
    );
}

// https://api.kinopoisk.dev/review?token=RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J&sortField=date&sortType=-1&limit=5&field=movieId&search=1143242