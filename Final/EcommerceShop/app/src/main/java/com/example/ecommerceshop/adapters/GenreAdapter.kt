package com.example.ecommerceshop.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceshop.R
import com.example.ecommerceshop.databinding.GenreItemBinding
import com.example.ecommerceshop.models.Genre

class GenreAdapter(val listener: Listener): RecyclerView.Adapter<GenreAdapter.GenreHolder>() {

    var list: List<Genre> = emptyList()

    class GenreHolder(item: View): RecyclerView.ViewHolder(item) {
        var binding = GenreItemBinding.bind(item)
        fun bind(genre: Genre, listener: Listener) = with(binding){
            genreName.text = genre.name
            cardView.setOnClickListener(){
                listener.onClick(genre)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.genre_item, parent, false)
        return GenreHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    interface Listener{
        fun onClick(genre: Genre)
    }
}