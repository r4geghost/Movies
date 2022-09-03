package com.example.movies.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.adapters.MovieAdapter;
import com.example.movies.data.models.Movie;
import com.example.movies.viewmodels.FavouriteMovieViewModel;

import java.util.List;

public class FavouriteMovieActivity extends AppCompatActivity {

    private static final String EXTRA_MOVIE = "movie";

    private FavouriteMovieViewModel viewModel;
    private RecyclerView recyclerViewFavouriteMovies;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_movie);

        initViews();

        // when we click on movie, show detail info
        movieAdapter.setOnMovieClickListener(new MovieAdapter.OnMovieClickListener() {
            @Override
            public void OnMovieClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(
                        FavouriteMovieActivity.this,
                        movie
                );
                startActivity(intent);
            }
        });

        viewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });
    }

    private void initViews() {
        viewModel = new ViewModelProvider(this).get(FavouriteMovieViewModel.class);
        recyclerViewFavouriteMovies = findViewById(R.id.recyclerViewFavouriteMovies);
        movieAdapter = new MovieAdapter();
        recyclerViewFavouriteMovies.setAdapter(movieAdapter);
    }

    // starts FavouriteMovieActivity
    public static Intent newIntent(Context context) {
        return new Intent(context, FavouriteMovieActivity.class);
    }
}