package com.example.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();

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
        // set rating
        holder.textViewRating.setText(movie.getRating().getKinopoisk());
        // set background color to rating
        // 1. getting color ID
        int colorResId;
        // convert rating from String to Double
        double rating = Double.parseDouble(movie.getRating().getKinopoisk());
        if (rating < 5.0) {
            colorResId = android.R.color.holo_red_light;
        } else if (rating < 7.0) {
            colorResId = android.R.color.holo_orange_light;
        }
        else {
            colorResId = android.R.color.holo_green_light;
        }
        // 2. getting color by its ID
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        // 3. setting background color to view
        holder.textViewRating.setBackgroundColor(color);
        // set movie name
        holder.textViewName.setText(movie.getName());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    protected static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPoster;
        private final TextView textViewRating;
        private final TextView textViewName;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewName = itemView.findViewById(R.id.textViewName);
        }
    }

}
