package com.example.ecommerceshop.screens.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ecommerceshop.R
import com.example.ecommerceshop.databinding.FragmentCreateBinding
import com.example.ecommerceshop.databinding.FragmentGenreItemBinding
import com.example.ecommerceshop.databinding.GenreItemBinding
import com.example.ecommerceshop.models.Genre

class GenreItem : Fragment() {

    private lateinit var binding: FragmentGenreItemBinding
    companion object{
        lateinit var genre: Genre
    }

    fun pasGenre(genre_: Genre){
        genre = genre_
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentGenreItemBinding.inflate(inflater, container, false)
        binding.title.text = genre.name
        binding.desc.text = genre.description
        return binding.root
    }
}