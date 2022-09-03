package com.example.movies.data.database;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.movies.data.models.Movie;

@Database(version = 1, entities = {Movie.class}, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {

    private static final String DB_NAME = "movies.db";

    public abstract MovieDao movieDao();

    private static MovieDatabase instance;

    public static MovieDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    application,
                    MovieDatabase.class,
                    DB_NAME
            ).build();
        }
        return instance;
    }
}
