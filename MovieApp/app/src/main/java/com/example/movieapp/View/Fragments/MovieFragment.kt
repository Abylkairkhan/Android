package com.example.movieapp.View.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.View.Adapters.MoviesAdapter
import com.example.movieapp.ViewModel.MovieViewModel
import com.example.movieapp.databinding.FragmentMovieBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapter: MoviesAdapter
    private val model: MovieViewModel = MovieViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        initRecView()
        return binding.root
    }

    private fun initRecView(){
        adapter = MoviesAdapter()
        model.movies.observe(viewLifecycleOwner) {
            model.loadMovieList()
            adapter.movies = it
        }
        val layoutManager = LinearLayoutManager(activity)
        binding.RecView.layoutManager = layoutManager
        binding.RecView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieFragment()
    }
}
