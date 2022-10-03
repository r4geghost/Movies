package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.data.models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    private final static String TYPE_POSITIVE = "Позитивный";
    private final static String TYPE_NEGATIVE = "Негативный";
    private final static String TYPE_NEUTRAL = "Нейтральный";

    private List<Review> reviews = new ArrayList<>();

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new ReviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewHolder holder, int position) {
        Review review = reviews.get(position);

        holder.textViewUsername.setText(review.getAuthor());
        // get review title
        String reviewTitle = review.getTitle();
        if (reviewTitle == null) {
            // if there is no title of review,
            // stay default
        } else {
            holder.textViewReviewTitle.setText(review.getTitle());
        }
        holder.textViewReview.setText(review.getDescription());
        // set background color to review
        // 1. getting color ID
        int colorResId;
        String reviewType = review.getType();
        if (reviewType == null) {
            reviewType = TYPE_NEUTRAL;
        }
        switch (reviewType) {
            case TYPE_POSITIVE:
                colorResId = android.R.color.holo_green_dark;
                break;
            case TYPE_NEGATIVE:
                colorResId = android.R.color.holo_red_dark;
                break;
            default:
                colorResId = android.R.color.holo_orange_dark;
        }
        // 2. getting color by its ID
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        // 3. setting background color to CardView
        holder.cardViewReview.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    protected static class ReviewHolder extends RecyclerView.ViewHolder {

        private final CardView cardViewReview;
        private final TextView textViewUsername;
        private final TextView textViewReviewTitle;
        private final TextView textViewReview;

        public ReviewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewReview = itemView.findViewById(R.id.cardViewReview);
            textViewUsername = itemView.findViewById(R.id.textViewUsername);
            textViewReviewTitle = itemView.findViewById(R.id.textViewReviewTitle);
            textViewReview = itemView.findViewById(R.id.textViewReview);
        }
    }
}
