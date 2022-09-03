package com.example.movies.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.api.ApiFactory;
import com.example.movies.data.database.MovieDao;
import com.example.movies.data.database.MovieDatabase;
import com.example.movies.data.models.Movie;
import com.example.movies.data.models.Person;
import com.example.movies.data.models.Review;
import com.example.movies.data.models.Trailer;
import com.example.movies.data.response.PersonResponse;
import com.example.movies.data.response.ReviewResponse;
import com.example.movies.data.response.TrailerResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MovieDetailViewModel extends AndroidViewModel {

    private static final String TAG = "MovieDetailViewModel";

    private final MutableLiveData<List<Person>> persons = new MutableLiveData<>();

    public LiveData<List<Person>> getPersons() {
        return persons;
    }

    private final MutableLiveData<List<Trailer>> trailers = new MutableLiveData<>();

    public LiveData<List<Trailer>> getTrailers() {
        return trailers;
    }

    private final MutableLiveData<List<Review>> reviews = new MutableLiveData<>();

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public LiveData<Movie> getFavouriteMovie(long movieId) {
        return movieDao.getMovieById(movieId);
    }

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private final MovieDao movieDao;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(getApplication()).movieDao();
    }

    public void loadPersons(long movieId) {
        Disposable disposable = ApiFactory.apiService.loadPersons(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // instead of receiving PersonResponse object,
                // we get List<Person> inside of it
                .map(new Function<PersonResponse, List<Person>>() {
                    @Override
                    public List<Person> apply(PersonResponse personResponse) throws Throwable {
                        return personResponse.getPersons();
                    }
                })
                // now we have direct access to list of persons
                .subscribe(new Consumer<List<Person>>() {
                    @Override
                    public void accept(List<Person> personsList) throws Throwable {
                        persons.setValue(personsList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadTrailers(long movieId) {
        Disposable disposable = ApiFactory.apiService.loadTrailers(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<TrailerResponse, List<Trailer>>() {
                    @Override
                    public List<Trailer> apply(TrailerResponse trailerResponse) throws Throwable {
                        return trailerResponse.getTrailersList().getTrailers();
                    }
                })
                .subscribe(new Consumer<List<Trailer>>() {
                    @Override
                    public void accept(List<Trailer> trailersList) throws Throwable {
                        trailers.setValue(trailersList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void loadReviews(long movieId) {
        Disposable disposable = ApiFactory.apiService.loadReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewResponse, List<Review>>() {
                    @Override
                    public List<Review> apply(ReviewResponse reviewResponse) throws Throwable {
                        return reviewResponse.getReviews();
                    }
                })
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviewsList) throws Throwable {
                        reviews.setValue(reviewsList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    // add favourite movie to db with RxJava
    public void insertMovie(Movie movie) {
        Disposable disposable = movieDao.insertMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    // delete favourite movie from db with RxJava
    public void deleteMovie(long movieId) {
        Disposable disposable = movieDao.deleteMovie(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe();
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
