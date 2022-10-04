package com.example.movies.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.movies.R;
import com.example.movies.data.models.Review;

public class ReviewDetailActivity extends AppCompatActivity {

    private final static String TYPE_POSITIVE = "Позитивный";
    private final static String TYPE_NEGATIVE = "Негативный";
    private final static String TYPE_NEUTRAL = "Нейтральный";

    private static final String EXTRA_REVIEW = "review";

    private TextView textViewUsername;
    private TextView textViewReviewTitle;
    private TextView textViewReviewText;
    private TextView textViewLikesCount;
    private TextView textViewDislikesCount;
    private LinearLayout reviewLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_detail);
        initViews();
        // get review from previous activity
        Review review = (Review) getIntent().getSerializableExtra(EXTRA_REVIEW);
        // Log.d("Review", review.toString());
        setUpViews(review);
    }

    private void initViews() {
        reviewLinearLayout = findViewById(R.id.reviewLinearLayout);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewReviewTitle = findViewById(R.id.textViewReviewTitle);
        textViewReviewText = findViewById(R.id.textViewReviewText);
        textViewLikesCount = findViewById(R.id.textViewLikesCount);
        textViewDislikesCount = findViewById(R.id.textViewDislikesCount);
    }

    private void setUpViews(Review review) {
        textViewUsername.setText(review.getAuthor());
        String reviewTitle = review.getTitle();
        if (reviewTitle != null) {
            textViewReviewTitle.setText(reviewTitle);
        }
        textViewReviewText.setText(review.getDescription());
        textViewLikesCount.setText(String.valueOf(review.getReviewLikes()));
        textViewDislikesCount.setText(String.valueOf(review.getReviewDislikes()));
        // set background color
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
        int color = ContextCompat.getColor(ReviewDetailActivity.this, colorResId);
        // 3. setting background color to layout
        reviewLinearLayout.setBackgroundColor(color);
    }

    public static Intent newIntent(Context context, Review review) {
        Intent intent = new Intent(context, ReviewDetailActivity.class);
        intent.putExtra(EXTRA_REVIEW, review);
        return intent;
    }
}