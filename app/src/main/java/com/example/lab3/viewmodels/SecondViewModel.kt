package com.example.lab3.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab3.database.MovieDatabase
import com.example.lab3.models.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondViewModel(application: Application): AndroidViewModel(application) {

    private val db: MovieDatabase = MovieDatabase.getInstance(application)
    private var movie: MutableLiveData<Movie> = MutableLiveData()

    fun findMovieByTitle(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
           movie.postValue(db.movieDao().getAll(title)[0])
        }
    }
    fun getMovieMutableLiveData(): MutableLiveData<Movie>{
        return movie;
    }
}