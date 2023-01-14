package com.example.lab3.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lab3.api.OMDbApi
import com.example.lab3.api.OMDbApiClient
import com.example.lab3.database.MovieDatabase
import com.example.lab3.models.MovieList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirstViewModel(application: Application): AndroidViewModel(application) {

    private var app: Application = application
    private var OMDbAPIClient: OMDbApi = OMDbApiClient.getOMDbApi()!!
    private var movieList: MutableLiveData<MovieList> = MutableLiveData()
    private val db: MovieDatabase = MovieDatabase.getInstance(application)

    fun findMoviesByTitle(title: String){
        OMDbAPIClient.getMoviesByTitle("b0e647f4", "1", "movie", title).enqueue(object :
            Callback<MovieList> {
            override fun onResponse(call: Call<MovieList>?, response: Response<MovieList>) {
                if (response.body() != null){
                    val movies: MovieList = response.body()
                    saveMovie(movies)
                    movieList.postValue(movies)
                    Toast.makeText(app, "Success!", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(app, "Error", Toast.LENGTH_LONG).show()
                }

            }

            override fun onFailure(call: Call<MovieList>?, t: Throwable) {
                Toast.makeText(app, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun saveMovie(currentMovieList: MovieList) {
        CoroutineScope(Dispatchers.IO).launch {
            db.movieDao().insertMovie(currentMovieList.Search)
        }
    }

    fun getMovieList(): MutableLiveData<MovieList>{
        return movieList;
    }
}