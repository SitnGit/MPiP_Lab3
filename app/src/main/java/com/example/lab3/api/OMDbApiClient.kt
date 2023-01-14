package com.example.lab3.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OMDbApiClient {

    companion object {
        private var omdbApi: OMDbApi? = null
        val apiKey = "b0e647f4";

        fun getOMDbApi(): OMDbApi? {

            if(omdbApi == null) {
                omdbApi = Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OMDbApi::class.java)
            }

            return omdbApi
        }
    }

}