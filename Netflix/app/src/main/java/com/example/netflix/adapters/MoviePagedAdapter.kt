package com.example.netflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.netflix.databinding.MovieItemBinding
import com.example.netflix.models.Movie
import com.squareup.picasso.Picasso

private const val AD_ITEM_VIEW_TYPE = 1

class MoviePagedAdapter(val listener: Listener): PagingDataAdapter<Movie, MoviePagedAdapter.Holder>(
    MovieDiffCallBack()) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val movie = getItem(position) ?: return
        with(holder.binding){
            Picasso
                .get()
                .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.poster_path)
                .into(imageView)
            movieTitle.text = movie.title
            movieAdult.text = if(movie.adult) "Yes" else "No"
            movieVote.text = movie.vote_average.toString()
            cardView.setOnClickListener {
                listener.onClick(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return Holder(binding)
    }

    class Holder(
        val binding: MovieItemBinding
    ): RecyclerView.ViewHolder(binding.root)

    interface Listener{
        fun onClick(movie: Movie)
    }
}

class MovieDiffCallBack : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}