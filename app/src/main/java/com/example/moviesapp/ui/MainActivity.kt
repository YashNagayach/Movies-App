package com.example.moviesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesapp.adapter.MovieAdapter
import com.example.moviesapp.databinding.ActivityMainBinding
import com.example.moviesapp.model.Movie
import com.example.moviesapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.adapter = movieAdapter

        movieViewModel.moviesLiveData.observe(this) {
            when (it) {
                is NetworkResult.Success -> {
                    movieAdapter.updateMovies(it.data)
                    binding.progress.isVisible = false
                }

                is NetworkResult.Failure -> {
                    Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
                    binding.progress.isVisible = false
                }

                is NetworkResult.Loading -> {
                    binding.progress.isVisible = it.isLoading
                }
            }
        }

        movieAdapter.onItemClick = {
            DetailedActivity.start(this, it.overview, it.poster_path)
        }

    }
}