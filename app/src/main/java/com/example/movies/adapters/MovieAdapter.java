package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.data.models.Movie;
import com.example.movies.R;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();

    private OnReachEndListener onReachEndListener;

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    private OnMovieClickListener onMovieClickListener;

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item,
                parent,
                false
        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        // set poster image via Glide
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.imageViewPoster);
        // set background color to rating
        // 1. getting color ID
        int colorResId;
        // get Kinopoisk rating
        double rating = movie.getRating().getKinopoisk();
        if (rating < 5) {
            colorResId = android.R.color.holo_red_light;
        } else if (rating < 7) {
            colorResId = android.R.color.holo_orange_light;
        } else {
            colorResId = android.R.color.holo_green_light;
        }
        // 2. getting color by its ID
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        // 3. setting background color to TextView
        holder.textViewRating.setBackgroundColor(color);
        // convert rating from double to String and set it to TextView
        holder.textViewRating.setText(String.valueOf(rating));
        // set movie title
        holder.textViewTitle.setText(movie.getTitle());

        // if we are near the end of the movie list,
        // call onReachEndListener (realisation in Activity)
        if (position == movies.size() - 1 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }

        // set onMovieClickListener (realisation in Activity)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onMovieClickListener != null) {
                    onMovieClickListener.OnMovieClick(movie);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public interface OnReachEndListener {
        void onReachEnd();
    }

    public interface OnMovieClickListener {
        void OnMovieClick(Movie movie);
    }

    protected static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPoster;
        private final TextView textViewRating;
        private final TextView textViewTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
        }
    }

}
