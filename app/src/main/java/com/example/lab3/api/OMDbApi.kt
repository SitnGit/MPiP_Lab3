package com.example.lab3.api

import com.example.lab3.models.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbApi {

    @GET(".")
    fun getMoviesByTitle(@Query("apikey") apiKey: String,
                         @Query("page") page: String,
                         @Query("type") type: String,
                         @Query("s") s: String): Call<MovieList>
}