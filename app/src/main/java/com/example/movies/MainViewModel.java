package com.example.movies;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    // for test only
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
        isLoading.setValue(true);
        Disposable disposable = ApiFactory.apiService.loadMovies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MoviesResponse>() {
                    @Override
                    public void accept(MoviesResponse moviesResponse) throws Throwable {
                        // set movies to LiveData object
                        movies.setValue(moviesResponse.getMovies());
                        // if we have successfully loaded movies,
                        // next time load new page
                        page++;
                        // and change boolean LiveData because
                        // loading is finished
                        isLoading.setValue(false);
                        // logging
                        Log.d(TAG, movies.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        // logging
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
