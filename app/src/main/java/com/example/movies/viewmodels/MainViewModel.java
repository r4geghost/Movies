package com.example.movies.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.api.ApiFactory;
import com.example.movies.data.models.Movie;
import com.example.movies.data.response.MovieResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = "MainViewModel";

    // LiveData object that handles changes in list of movies
    private final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
    // collection of Disposable objects
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    // LiveData object that shows if movies is loading
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    // current page
    private int page = 1;

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    // load list of movies in RxJava
    public void loadMovies() {
        // show that loading has started
        isLoading.setValue(true);
        Disposable disposable = ApiFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MovieResponse>() {
                    @Override
                    public void accept(MovieResponse moviesResponse) throws Throwable {
                        // get previously loaded movies stored in LiveData
                        List<Movie> loadedMovies = movies.getValue();
                        // if we had previously loaded movies in LiveData,
                        // then add new values to that LiveData,
                        // else - set new value to moviesResponse.getMovies()
                        if (loadedMovies != null) {
                            loadedMovies.addAll(moviesResponse.getMovies());
                            movies.setValue(loadedMovies);
                        } else {
                            // set movies to LiveData object
                            movies.setValue(moviesResponse.getMovies());
                        }
                        // if we have successfully loaded movies,
                        // next time load new page
                        page++;
                        // show that loading has finished
                        isLoading.setValue(false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        // logging error
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
