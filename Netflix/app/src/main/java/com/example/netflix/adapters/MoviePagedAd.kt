package com.example.netflix.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.netflix.R
import com.example.netflix.databinding.AdItemBinding
import com.example.netflix.databinding.MovieItemBinding
import com.example.netflix.models.Movie

class MoviePagedAd(val listener: Listener): PagingDataAdapter<Movie, MainRecViewHolder>(MovieDiffCallBack()) {

    private val AD_ITEM_VIEW_TYPE = 1

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 6 == 0) {
            AD_ITEM_VIEW_TYPE
        } else {
            R.layout.movie_item
        }
    }

    override fun onBindViewHolder(holder: MainRecViewHolder, position: Int) {
        return when(holder){
            is MainRecViewHolder.MovieViewHolder -> holder.bind(getItem(position) as Movie, listener)
            is MainRecViewHolder.AdViewHolder -> holder.bind(null )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecViewHolder {
        return when(viewType){
            R.layout.movie_item -> MainRecViewHolder.MovieViewHolder(
                MovieItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            AD_ITEM_VIEW_TYPE -> MainRecViewHolder.AdViewHolder(
                AdItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Error")
        }
    }

    interface Listener{
        fun onClick(movie: Movie)
    }
}