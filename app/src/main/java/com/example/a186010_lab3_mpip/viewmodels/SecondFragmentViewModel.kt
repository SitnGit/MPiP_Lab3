package com.example.a186010_lab3_mpip.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.a186010_lab3_mpip.database.AppDatabase
import com.example.a186010_lab3_mpip.models.Data
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val database: AppDatabase = AppDatabase.getInstance(application)
    private var movieLiveData: MutableLiveData<Data> = MutableLiveData()

    fun findMovieByTitle(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
           movieLiveData.postValue(database.movieDao().getMovies(title)[0])
        }
    }
    fun getMovieMutableLiveData(): MutableLiveData<Data>{
        return movieLiveData;
    }
}