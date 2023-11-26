package com.example.moviesapp.model

import com.google.gson.annotations.SerializedName

data class MovieData(
    val dates: Dates,
    val page: Int,
    @SerializedName("results")
    val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)