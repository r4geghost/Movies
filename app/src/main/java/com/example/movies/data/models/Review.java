package com.example.movies.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {

    @SerializedName("author")
    private String author;
    @SerializedName("title")
    private String title;
    @SerializedName("review")
    private String description;
    @SerializedName("type")
    private String type;
    @SerializedName("reviewDislikes")
    private int reviewDislikes;
    @SerializedName("reviewLikes")
    private int reviewLikes;

    public Review(String author, String title, String description, String type, int reviewDislikes, int reviewLikes) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.type = type;
        this.reviewDislikes = reviewDislikes;
        this.reviewLikes = reviewLikes;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public int getReviewDislikes() {
        return reviewDislikes;
    }

    public int getReviewLikes() {
        return reviewLikes;
    }

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", reviewDislikes=" + reviewDislikes +
                ", reviewLikes=" + reviewLikes +
                '}';
    }
}
