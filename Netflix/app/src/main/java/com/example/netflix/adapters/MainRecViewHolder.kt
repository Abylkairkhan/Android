package com.example.netflix.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.netflix.databinding.AdItemBinding
import com.example.netflix.databinding.MovieItemBinding
import com.example.netflix.models.Movie

sealed class MainRecViewHolder(binding: ViewBinding): RecyclerView.ViewHolder(binding.root) {

    class MovieViewHolder(private val binding: MovieItemBinding): MainRecViewHolder(binding){
        fun bind(movie: Movie, listener: MoviePagedAd.Listener){
            with(binding){
                com.squareup.picasso.Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.poster_path).into(imageView)
                movieTitle.text = movie.title
                movieAdult.text = if(movie.adult) "Yes" else "No"
                movieVote.text = movie.vote_average.toString()
                cardView.setOnClickListener {
                    listener.onClick(movie)
                }
            }
        }
    }

    class AdViewHolder(private val binding: AdItemBinding): MainRecViewHolder(binding){
        fun bind(movie: Movie?) {

        }
    }
}