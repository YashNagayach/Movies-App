package com.example.moviesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesapp.model.Movie
import com.example.moviesapp.repository.MovieRepository
import com.example.moviesapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private var _moviesLiveData = MutableLiveData<NetworkResult<List<Movie>>>()

    val moviesLiveData: LiveData<NetworkResult<List<Movie>>>
        get() = _moviesLiveData

    init {
        getMoviesData()
    }

    private fun getMoviesData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMoviesData().collect {
                _moviesLiveData.postValue(it)
            }
        }
    }
}