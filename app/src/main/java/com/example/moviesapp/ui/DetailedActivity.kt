package com.example.moviesapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moviesapp.R
import com.example.moviesapp.model.Movie

class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val movieDesc = intent.getStringExtra("movie")
        val imageUrl = intent.getStringExtra("image")



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