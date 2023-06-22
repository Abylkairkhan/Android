package com.example.netflix_compose.screens.DetailsScreen

import androidx.navigation.NavController
import com.example.netflix.models.Movie

sealed class DetailState {

    object Empty: DetailState()

    object ShowProgress: DetailState()

    data class ShowMovie(var movie: Movie): DetailState()

    data class ShowError(var e: Exception): DetailState()
}

sealed class DetailEvent {

    data class NavigateBack(var navController: NavController): DetailEvent()

    data class WaitMovie(var movie_id: Int, var type: Boolean): DetailEvent()

    data class SaveMovie(val movie: Movie): DetailEvent()

    data class DeleteMovie(val movie: Movie): DetailEvent()
}