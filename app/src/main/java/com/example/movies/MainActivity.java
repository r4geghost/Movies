package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    // Kinopoisk API token
    private static final String TOKEN = "RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J";

    private MainViewModel viewModel;
    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;
    private ProgressBar progressBar;

    // shows if onReachEnd() was called
    private boolean onReachEndCall = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        // subscribe to changes in list of movies
        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                // add movies to adapter
                movieAdapter.setMovies(movies);
            }
        });

        // subscribe to changes in loading
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                // if movies are loading and onReachEnd()
                // wasn't called, then show progress bar
                // else - disable it (set visibility to GONE)
                if (isLoading && !onReachEndCall) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        // when user has reached the end of 
        // the movies list, start loading next page
        movieAdapter.setOnReachEndListener(new MovieAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                // update boolean
                onReachEndCall = true;
                // load new page on movies
                viewModel.loadMovies();
            }
        });

        // load movies from API
        viewModel.loadMovies();
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        movieAdapter = new MovieAdapter();
        recyclerViewMovies.setAdapter(movieAdapter);
        progressBar = findViewById(R.id.progressBarLoading);
    }
}