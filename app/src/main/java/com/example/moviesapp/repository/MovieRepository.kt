package com.example.moviesapp.repository

import com.example.moviesapp.api.MovieApi
import com.example.moviesapp.utils.NetworkResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieApi) {

    suspend fun getMoviesData() = flow {
        emit(NetworkResult.Loading(true))
        val response = api.getMovieData()
        if (response.isSuccessful && response.body() != null) {
            emit(NetworkResult.Success(response.body()!!.movies))
        } else {
            emit(NetworkResult.Failure("message of failure ${response.message()}"))
        }
    }.catch {
        emit(NetworkResult.Failure("Error message is $it"))
    }

}