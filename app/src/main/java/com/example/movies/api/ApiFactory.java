package com.example.movies.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

// helper class for Retrofit
public class ApiFactory {

    private static final String BASE_URL = "https://api.kinopoisk.dev/";

    // create Retrofit object
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    // another realisation of Singleton pattern
    public static final ApiService apiService = retrofit.create(ApiService.class);
}
