package com.example.movies.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    // movie identifier
    @SerializedName("id")
    private long id;
    // movie title
    @SerializedName("name")
    private String title;
    // movie description
    @SerializedName("description")
    private String description;
    // film release year
    @SerializedName("year")
    private int year;
    // movie poster
    @SerializedName("poster")
    private Poster poster;
    // movie rating
    @SerializedName("rating")
    private Rating rating;

    public Movie(long id, String title, String description, int year, Poster poster, Rating rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.poster = poster;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getYear() {
        return year;
    }

    public Poster getPoster() {
        return poster;
    }

    public Rating getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", poster=" + poster +
                ", rating=" + rating +
                '}';
    }
}
