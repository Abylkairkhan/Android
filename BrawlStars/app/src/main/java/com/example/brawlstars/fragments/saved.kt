package com.example.brawlstars.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brawlstars.R
import com.example.brawlstars.adapter.MovieAdapter
import com.example.brawlstars.databinding.FragmentSavedBinding
import com.example.brawlstars.models.Movies
import com.example.brawlstars.room.maindb

class saved : Fragment(), MovieAdapter.Listener {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var db: maindb
    private val item = item()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        initRecView()
        return binding.root
    }

    private fun initRecView(){
        with(binding){
            var list = listOf<Movies>()
            Thread{
                db = maindb.getdb(requireContext())
                list = db.getDao().getMovies()
            }.start()

            while (list.isEmpty()){
                Thread.sleep(100)
            }

            var adapter = MovieAdapter(this@saved)
            adapter.list = list
            RecView.adapter = adapter
            RecView.layoutManager = LinearLayoutManager(activity)
        }
    }

    override fun onClick(movies: Movies) {
        item.setType(false)
        item.setMovie(movies)
        findNavController().navigate(R.id.action_savedFragment_to_item)
    }
}