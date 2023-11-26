package com.example.moviesapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.moviesapp.R
import com.example.moviesapp.databinding.ActivityDetailedBinding
import com.example.moviesapp.model.Movie

class DetailedActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieDesc = intent.getStringExtra("movie")
        val imageUrl = "https://image.tmdb.org/t/p/w185/${intent.getStringExtra("image")}"

        Glide.with(this)
            .load(imageUrl)
            .into(binding.image)

        binding.title.text = movieDesc

    }

    companion object {
        fun start(origin: Activity, movieDesc: String, imageUrl: String) {
            origin.startActivity(
                Intent(origin, DetailedActivity::class.java).apply {
                    putExtra("movie", movieDesc)
                    putExtra("image", imageUrl)
                }
            )
        }
    }
}