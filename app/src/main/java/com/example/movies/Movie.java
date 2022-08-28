package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Movie {

    // movie identifier
    @SerializedName("id")
    private long id;
    // movie name
    @SerializedName("name")
    private String name;
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

    public Movie(long id, String name, String description, int year, Poster poster, Rating rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.poster = poster;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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
}
