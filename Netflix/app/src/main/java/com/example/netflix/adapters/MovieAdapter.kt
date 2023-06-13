package com.example.netflix.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.R
import com.example.netflix.databinding.MovieItemBinding
import com.example.netflix.models.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(val listener: Listener): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    var list: List<Movie> = emptyList()

    class MovieHolder(item: View): RecyclerView.ViewHolder(item){
        var binding = MovieItemBinding.bind(item)

        fun bind(movie: Movie, listener: Listener) = with(binding){
            Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.poster_path).into(imageView)
            movieTitle.text = movie.title
            movieAdult.text = if(movie.adult) "Yes" else "No"
            movieVote.text = movie.vote_average.toString()
            cardView.setOnClickListener(){
                listener.onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    interface Listener{
        fun onClick(movie: Movie)
    }
}