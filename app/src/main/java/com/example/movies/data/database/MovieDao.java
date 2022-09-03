package com.example.movies.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.movies.data.models.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM favouriteMovies")
    LiveData<List<Movie>> getAllFavoriteMovies();

    @Query("SELECT * FROM favouriteMovies WHERE id = :movieId")
    LiveData<Movie> getMovieById(long movieId);

    @Insert
    Completable insertMovie(Movie movie);

    @Query("DELETE FROM favouriteMovies WHERE id = :movieId")
    Completable deleteMovie(long movieId);
}
