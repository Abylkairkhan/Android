package com.example.brawlstars.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.brawlstars.R
import com.example.brawlstars.databinding.FragmentItemBinding
import com.example.brawlstars.models.Movies
import com.example.brawlstars.request.service
import com.example.brawlstars.room.maindb
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

class item : Fragment() {

    companion object{
        var movies = Movies(false,"",null,"","",0.0,"","",0,"","","",0.0)
        var type: Boolean = false
        var _id: Int = -1
    }

    fun setMovie(data: Movies){
        movies = data
    }

    fun setType(data: Boolean){
        type = data
    }

    fun setID(data: Int){
        _id = data
    }

    private lateinit var binding: FragmentItemBinding
    private val service = service()
    private lateinit var db: maindb
    private lateinit var movie: Movies

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemBinding.inflate(inflater, container, false)

        if(!type){
            binding.saveBtn.text = "Delete"
        }

        init()
        save()
        return binding.root
    }

    private fun save(){
        if(!type){
            binding.saveBtn.setOnClickListener() {
                Thread {
                    db = maindb.getdb(requireContext())
                    db.getDao().deleteMovieByID(movie.id!!)
                }.start()
                Toast.makeText(requireContext(), "Movie Deleted", Toast.LENGTH_LONG).show()
            }
        }
        else {
            binding.saveBtn.setOnClickListener() {
                Thread {
                    db = maindb.getdb(requireContext())
                    db.getDao().insert(movie)
                }.start()
                Toast.makeText(requireContext(), "Movie Saved", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun init(){
        if (!type){
            movie = movies
            with(binding) {
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.poster_path)
                    .into(imageView)
                title.text = movie.title
                description.text = movie.overview
                runtime.text = movie.runtime.toString() + "min"
                status.text = movie.status
            }
        }
        else {
            CoroutineScope(Dispatchers.IO).launch {
                movie = service.getMovieByID(_id)
                withContext(Dispatchers.Main) {
                    with(binding) {
                        Picasso.get()
                            .load("https://image.tmdb.org/t/p/w600_and_h900_bestv2" + movie.poster_path)
                            .into(imageView)
                        title.text = movie.title
                        description.text = movie.overview
                        runtime.text = movie.runtime.toString() + "min"
                        status.text = movie.status
                    }
                }
            }
        }
    }
}