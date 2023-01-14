package com.example.lab3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab3.models.Movie

@Database(entities = [Movie::class], version = 3)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private var dbInstance: MovieDatabase? = null

        fun getInstance(context: Context): MovieDatabase {
            if (dbInstance == null) {
                dbInstance = createInstance(context)
            }
            return dbInstance!!
        }

        private fun createInstance(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context,
                MovieDatabase::class.java, "movielist.db"
            ).fallbackToDestructiveMigration().build()
        }
    }
}