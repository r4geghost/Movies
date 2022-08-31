package com.example.movies.data.response;

import com.example.movies.data.models.Review;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReviewResponse {

    // list of movies we get as
    // response from Kinopoisk API
    @SerializedName("docs")
    private List<Review> reviews;

    public ReviewResponse(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviews=" + reviews +
                '}';
    }
}
