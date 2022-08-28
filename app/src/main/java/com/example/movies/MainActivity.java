package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Kinopoisk API token
    private static final String TOKEN = "RWPCVHX-W5W4JJN-P6JZ0D7-BBCEF4J";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}