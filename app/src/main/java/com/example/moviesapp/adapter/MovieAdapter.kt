package com.example.moviesapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.MovieItemBinding
import com.example.moviesapp.model.Movie
import javax.inject.Inject

class MovieAdapter @Inject constructor(): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    var onItemClick: ((Movie) -> Unit)? = null
    private var movies = mutableListOf<Movie>()

    fun updateMovies(moviesList: List<Movie>) {
        movies.clear()
        movies = moviesList.toMutableList()
        notifyItemRangeInserted(0, movies.size)
    }

    inner class MovieViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = movies[position]
        val imageUrl = "https://image.tmdb.org/t/p/w185/${movie.poster_path}"
        holder.binding.title.text = movie.title

        Glide.with(holder.binding.image.context)
            .load(imageUrl)
            .into(holder.binding.image)

        holder.binding.parentLayout.setOnClickListener {
            Log.d("Taskkk", "called and listener invoked from onBindViewHolder with global listener value as $onItemClick")
            onItemClick?.invoke(movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}