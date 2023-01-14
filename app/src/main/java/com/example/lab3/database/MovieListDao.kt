package com.example.lab3.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab3.models.Movie

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMovie(data: MutableList<Movie>)

    @Query("SELECT * FROM Movie WHERE title = :title")
    abstract fun getAll(title: String): List<Movie>
}