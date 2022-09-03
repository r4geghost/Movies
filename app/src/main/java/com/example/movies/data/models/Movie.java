package com.example.movies.data.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "favouriteMovies")
public class Movie implements Serializable {

    @PrimaryKey
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
    @Embedded
    private Poster poster;
    // movie rating
    @SerializedName("rating")
    @Embedded
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
