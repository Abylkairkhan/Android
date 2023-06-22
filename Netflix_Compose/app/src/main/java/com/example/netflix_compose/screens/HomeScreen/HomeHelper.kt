package com.example.netflix_compose.screens.HomeScreen

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.netflix.models.Movie

sealed class HomeState{
    object Empty: HomeState()
    object ShowProgress: HomeState()
    data class ShowMovies(var movies: List<Movie>): HomeState()
    data class ShowError(var error: Exception): HomeState()
}

sealed class HomeEvent{

    object LoadNextMovies: HomeEvent()
    data class DetailsEvent(var movie_id: Int, var navController: NavController, var type: Boolean): HomeEvent()
}