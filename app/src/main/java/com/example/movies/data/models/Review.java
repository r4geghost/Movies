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

    public Review(String author, String title, String description, String type) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.type = type;
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

    @Override
    public String toString() {
        return "Review{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
