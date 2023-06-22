package com.example.netflix_compose.screens.SavedScreen

import androidx.navigation.NavController
import com.example.netflix.models.Movie

sealed class SavedState {

    object Empty: SavedState()

    data class ShowMovies(val movies: List<Movie>): SavedState()

}

sealed class SavedEvent {

    data class DetailsEvent(var movie_id: Int, var navController: NavController, var type: Boolean): SavedEvent()

    object GetSavedMovies: SavedEvent()

}