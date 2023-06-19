package com.example.netflix_compose.screens.HomeScreen

import com.example.netflix.models.Movie

sealed class HomeState{
    object showProgres: HomeState()
    data class showMovies(var movies: List<Movie>): HomeState()
    data class showError(var error: Exception): HomeState()
}

sealed class HomeEvent{
    data class detailsEvent(var movie_id: Int): HomeEvent()
}