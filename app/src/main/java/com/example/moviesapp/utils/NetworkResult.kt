package com.example.moviesapp.utils

sealed class NetworkResult<T>(val data: T?, val message: String? = null) {
    class Loading<T>(val isLoading:Boolean): NetworkResult<T>(null)
    class Success<T>(data: T): NetworkResult<T>(data)
    class Failure<T>(message: String): NetworkResult<T>(null, message)

}