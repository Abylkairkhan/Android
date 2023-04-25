package com.example.movieapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.Model.Server
import com.example.movieapp.Model.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel(): ViewModel() {

    private val server = Server()

    private val _movies = MutableLiveData<List<Movie>>()
    var movies: LiveData<List<Movie>> = _movies

     fun loadMovieList() {
        CoroutineScope(Dispatchers.IO).launch {
            _movies.postValue(server.getListOfMovies())
        }
         movies = _movies
    }
}