package com.example.movies;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

// interface with Retrofit request
public interface ApiService {

    // TODO: change it later
    @GET("movie?token=RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1&limit=30")
    Single<MoviesResponse> loadMovies(
            @Query("page") int page
    );
}
// url
// movie?token=RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J&field=rating.kp&search=7-10&sortField=votes.kp&sortType=-1
