package com.example.movies.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.adapters.PersonAdapter;
import com.example.movies.adapters.ReviewAdapter;
import com.example.movies.adapters.TrailerAdapter;
import com.example.movies.data.models.Movie;
import com.example.movies.data.models.Person;
import com.example.movies.data.models.Review;
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
    private ImageView imageViewFavourite;
    private TextView textViewTrailerLabel;

    private MovieDetailViewModel viewModel;

    private RecyclerView recyclerViewPersons;
    private PersonAdapter personAdapter;
    private RecyclerView recyclerViewTrailers;
    private TrailerAdapter trailerAdapter;
    private RecyclerView recyclerViewReviews;
    private ReviewAdapter reviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        initViews();

        // get movie from intent
        Movie movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        setUpViews(movie);

        viewModel.getPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> persons) {
                personAdapter.setPeople(persons);
            }
        });
        viewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                trailerAdapter.setTrailers(trailers);
                // if there are no trailers, disable Trailers label
                if (trailers.size() == 0) {
                    textViewTrailerLabel.setVisibility(View.GONE);
                }
            }
        });
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviewList) {
                reviewAdapter.setReviews(reviewList);
            }
        });

        // load persons by movie id
        viewModel.loadPersons(movie.getId());
        // load trailers by movie id
        viewModel.loadTrailers(movie.getId());
        // load reviews by movie id
        viewModel.loadReviews(movie.getId());

        // show trailer on click
        trailerAdapter.setOnTrailerClickListener(new TrailerAdapter.OnTrailerClickListener() {
            @Override
            public void onTrailerClick(Trailer trailer) {
                // implicit intent - will open the browser
                Intent intent = new Intent(Intent.ACTION_VIEW);
                // parse url of trailer
                intent.setData(Uri.parse(trailer.getUrl()));
                startActivity(intent);
            }
        });

        // creating drawables for favourite icon
        // IS favourite
        Drawable starOn = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.btn_star_big_on
        );
        // IS NOT favourite
        Drawable starOff = ContextCompat.getDrawable(
                MovieDetailActivity.this,
                android.R.drawable.btn_star_big_off
        );
        // subscribe to changes of favourite movies
        viewModel.getFavouriteMovie(movie.getId()).observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movieFromDb) {
                if (movieFromDb == null) {
                    // change icon
                    imageViewFavourite.setImageDrawable(starOff);
                    imageViewFavourite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // add movie
                            viewModel.insertMovie(movie);
                        }
                    });
                } else {
                    // change icon
                    imageViewFavourite.setImageDrawable(starOn);
                    imageViewFavourite.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // delete movie
                            viewModel.deleteMovie(movie.getId());
                        }
                    });
                }
            }
        });
    }

    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        imageViewFavourite = findViewById(R.id.imageViewFavourite);
        textViewTrailerLabel = findViewById(R.id.textViewTrailerLabel);
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
        // setting horizontal Grid Layout
        // (also can be set in layout file)
        recyclerViewPersons.setLayoutManager(
                new GridLayoutManager(
                        this,
                        3,
                        RecyclerView.HORIZONTAL,
                        false
                )
        );

        recyclerViewTrailers = findViewById(R.id.recyclerViewTrailers);
        trailerAdapter = new TrailerAdapter();
        recyclerViewTrailers.setAdapter(trailerAdapter);

        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
        reviewAdapter = new ReviewAdapter();
        recyclerViewReviews.setAdapter(reviewAdapter);
        recyclerViewReviews.setLayoutManager(
                new LinearLayoutManager(
                        this,
                        RecyclerView.HORIZONTAL,
                        false
                )
        );
    }

    public static Intent newIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}