package com.example.movieapp.View.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.Model.data.Movie
import com.example.movieapp.databinding.MovieItemListBinding
import com.squareup.picasso.Picasso

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    var movies: List<Movie> = emptyList()

    class MoviesViewHolder(val binding: MovieItemListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemListBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movies[position]
        with(holder.binding){
            mvTitle.text = movie.title
            mvAvg.text = movie.voteAverage.toString()
            Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.posterPath).into(mvPoster)
        }
    }
}