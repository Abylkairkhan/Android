package com.example.brawlstars.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.brawlstars.R
import com.example.brawlstars.databinding.ItemMovieLayoutBinding
import com.example.brawlstars.models.Movies
import com.squareup.picasso.Picasso

class MovieAdapter(val listener: Listener): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    var list: List<Movies> = emptyList()

    class MovieHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemMovieLayoutBinding.bind(view)
        fun bind(movies: Movies, listener: Listener){
            with(binding){
                Picasso.get().load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movies.poster_path).into(imageView)
                title.text = movies.title
                date.text = movies.release_date
                adult.text = "Adult: " + movies.adult.toString()
            }
            itemView.setOnClickListener(){
                listener.onClick(movies)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_layout, parent, false)
        return MovieHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    interface Listener{
        fun onClick(movies: Movies)
    }
}