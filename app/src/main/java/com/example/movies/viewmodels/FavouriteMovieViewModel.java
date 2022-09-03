package com.example.movies.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.data.database.MovieDao;
import com.example.movies.data.database.MovieDatabase;
import com.example.movies.data.models.Movie;

import java.util.List;

public class FavouriteMovieViewModel extends AndroidViewModel {

    private MovieDao movieDao;

    public LiveData<List<Movie>> getMovies() {
        return movieDao.getAllFavoriteMovies();
    }

    public FavouriteMovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }
}
