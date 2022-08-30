package com.example.movies;

import com.google.gson.annotations.SerializedName;

public class Rating {

    // movie rating on Kinopoisk
    @SerializedName("kp")
    private double kinopoisk;

    public Rating(double kinopoisk) {
        this.kinopoisk = kinopoisk;
    }

    public double getKinopoisk() {
        return kinopoisk;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kinopoisk + '\'' +
                '}';
    }
}

