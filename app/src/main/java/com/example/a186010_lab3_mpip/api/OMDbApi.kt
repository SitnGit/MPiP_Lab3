package com.example.a186010_lab3_mpip.api

import com.example.a186010_lab3_mpip.models.MovieList
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