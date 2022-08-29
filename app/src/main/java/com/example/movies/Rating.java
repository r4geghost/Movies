package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    // movie rating on Kinopoisk
    @SerializedName("kp")
    private String kinopoisk;

    public Rating(String kinopoisk) {
        this.kinopoisk = kinopoisk;
    }

    public String getKinopoisk() {
        return kinopoisk;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kinopoisk + '\'' +
                '}';
    }
}

