package com.example.moviesapp.api

import com.example.moviesapp.model.MovieData
import com.example.moviesapp.utils.Common.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/3/movie/now_playing")
    suspend fun getMovieData(@Query("api_key") apiKey: String = API_KEY): Response<MovieData>

}