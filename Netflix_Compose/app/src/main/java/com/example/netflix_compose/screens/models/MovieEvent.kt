package com.example.netflix_compose.screens.models

import com.example.netflix.models.Movie

sealed class MovieNetworkScreenState{
    object ShowProgressBar: MovieNetworkScreenState()
    data class ShowListOfMovie(val data: String): MovieNetworkScreenState()
    data class GoDetails(val id: Int): MovieNetworkScreenState()
    data class ShowError(val error: String): MovieNetworkScreenState()
}

sealed class MovieNetworkScreenEvent{
    object EnterScreen:  MovieNetworkScreenEvent()
    data class NavigateToDetails(val id: Int): MovieNetworkScreenEvent()
}