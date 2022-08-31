package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.data.models.Trailer;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {

    private List<Trailer> trailers = new ArrayList<>();

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrailerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailer_item,
                parent,
                false
        );
        return new TrailerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerHolder holder, int position) {
        Trailer trailer = trailers.get(position);

        holder.textViewTrailerTitle.setText(trailer.getName());
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    protected static class TrailerHolder extends RecyclerView.ViewHolder {

        private final TextView textViewTrailerTitle;

        public TrailerHolder(@NonNull View itemView) {
            super(itemView);
            textViewTrailerTitle = itemView.findViewById(R.id.textViewTrailerTitle);
        }
    }
}
