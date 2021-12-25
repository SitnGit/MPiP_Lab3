package com.example.a186010_lab3_mpip.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a186010_lab3_mpip.models.Data

@Dao
abstract class MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertDatas(data: MutableList<Data>)

    @Query("SELECT * FROM Data WHERE title = :title")
    abstract fun getMovies(title: String): List<Data>
}