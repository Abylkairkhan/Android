package com.example.netflix_compose.screens.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflix.models.Movie
import com.example.netflix.request.Service
import com.example.netflix_compose.EventHandler
import com.example.netflix_compose.repository.IMovieRepository
import com.example.netflix_compose.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import java.lang.Error

class HomeViewModel(): ViewModel(), EventHandler<HomeEvent> {

    private var repository: IMovieRepository = MovieRepository()
    private var _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.showProgres)
    var state = _state.asStateFlow()
    var page = 1

    init {
        showMovies()
    }

    override fun obtainEvent(event: HomeEvent) {
        when(event){
            is HomeEvent.detailsEvent -> { }
        }
    }
    private fun showMovies(){
        try {
            if (_state.value != HomeState.showProgres)
                _state.value = HomeState.showProgres
            var movies = listOf<Movie>()
            viewModelScope.launch {
                movies = repository.getPopularMovie(page++)

                withContext(Dispatchers.IO){
                    _state.value = HomeState.showMovies(movies)
                }
            }
        } catch(e: Exception) {
            _state.value = HomeState.showError(e)
        }
    }
}
