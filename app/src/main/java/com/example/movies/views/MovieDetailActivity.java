package com.example.movies.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.adapters.PersonAdapter;
import com.example.movies.adapters.TrailerAdapter;
import com.example.movies.data.models.Movie;
import com.example.movies.data.models.Person;
import com.example.movies.data.models.Trailer;
import com.example.movies.viewmodels.MovieDetailViewModel;

import java.util.List;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailActivity";

    private static final String EXTRA_MOVIE = "movie";

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewYear;
    private TextView textViewDescription;

    private MovieDetailViewModel viewModel;
    private RecyclerView recyclerViewPersons;
    private PersonAdapter personAdapter;
    private RecyclerView recyclerViewTrailers;
    private TrailerAdapter trailerAdapter;

    private SnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initViews();

        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        setUpViews(movie);

        viewModel.getPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> persons) {
                personAdapter.setPeople(persons);
                Log.d(TAG, persons.toString());
            }
        });
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailerAdapter.setTrailers(trailers);
            }
        });

        // load persons by movie id
        viewModel.loadPersons(movie.getId());
        // load trailers by movie id
        viewModel.loadTrailers(movie.getId());

        snapHelper.attachToRecyclerView(recyclerViewPersons);
    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);

        snapHelper = new LinearSnapHelper();
    }

    private void setUpViews(Movie movie) {
        Glide.with(MovieDetailActivity.this)
                .load(movie.getPoster().getUrl())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getTitle());
        textViewYear.setText(String.valueOf(movie.getYear()));
        textViewDescription.setText(movie.getDescription());

        viewModel = new ViewModelProvider(this).get(MovieDetailViewModel.class);

        recyclerViewPersons = findViewById(R.id.recyclerViewPersons);
        personAdapter = new PersonAdapter();
        recyclerViewPersons.setAdapter(personAdapter);
        recyclerViewPersons.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        RecyclerView.HORIZONTAL,
                        false
                )
        );

        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        trailerAdapter = new TrailerAdapter();
        recyclerViewTrailers.setAdapter(trailerAdapter);
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}