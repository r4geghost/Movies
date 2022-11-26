package com.example.movies.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.adapters.MovieAdapter;
import com.example.movies.data.models.Movie;
import com.example.movies.viewmodels.FavouriteMovieViewModel;

import java.util.List;

public class FavouritesFragment extends Fragment {

    private FavouriteMovieViewModel viewModel;
    private RecyclerView recyclerViewFavouriteMovies;
    private MovieAdapter movieAdapter;

    private void initViews(View view) {
        viewModel = new ViewModelProvider(this).get(FavouriteMovieViewModel.class);
        recyclerViewFavouriteMovies = view.findViewById(R.id.recyclerViewFavouriteMovies);
        movieAdapter = new MovieAdapter();
        recyclerViewFavouriteMovies.setAdapter(movieAdapter);
    }

    public FavouritesFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);

        // when we click on movie, show detail info
        movieAdapter.setOnMovieClickListener(new MovieAdapter.OnMovieClickListener() {
            @Override
            public void onMovieClick(Movie movie) {
                Intent intent = MovieDetailActivity.newIntent(
                        requireContext(),
                        movie
                );
                startActivity(intent);
            }
        });

        viewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }
}