package com.example.netflix_compose.screens.NetworkMovieScreen

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.netflix_compose.screens.models.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieScreenViewModel: ViewModel(), EventHandler<MovieNetworkScreenEvent> {

    private var _movieViewState: MutableStateFlow<MovieNetworkScreenState> = MutableStateFlow(MovieNetworkScreenState.ShowProgressBar)
    val movieViewState = _movieViewState.asStateFlow()

    override fun obtainEvent(event: MovieNetworkScreenEvent) {
        when(val currentState = _movieViewState.value){
            is MovieNetworkScreenState.ShowProgressBar -> {
                Log.d("MyLog", "ShowProgressBarViewModel")
                reduce(event, currentState)
            }

            is MovieNetworkScreenState.ShowListOfMovie -> { reduce(event, currentState) }

            is MovieNetworkScreenState.GoDetails ->{ reduce(event, currentState) }

            is MovieNetworkScreenState.ShowError -> {  }

            else -> throw NotImplementedError("Unexpected State $currentState")
        }
    }

    private fun reduce(event: MovieNetworkScreenEvent, oldState: MovieNetworkScreenState.GoDetails){
        when(event){
            is MovieNetworkScreenEvent.NavigateToDetails -> {
                Log.d("MyLog", "sdfsdfsdf")
                fetchMovie(flag = true, id = event.id)
            }
            else -> {
                Log.d("MyLog", "$event.")
            }
        }
    }

    private fun reduce(event: MovieNetworkScreenEvent, oldState: MovieNetworkScreenState.ShowProgressBar){
        when(event){
            is MovieNetworkScreenEvent.NavigateToDetails -> {
                Log.d("MyLog", "NavigateToDetails")
                fetchMovie(flag = true, id = event.id)
            }
            else -> {}
        }
    }

    private fun reduce(event: MovieNetworkScreenEvent, oldState: MovieNetworkScreenState.ShowListOfMovie){
        when(event){
            is MovieNetworkScreenEvent.EnterScreen -> {  fetchMovie(true)}
            else -> {}
        }
    }

    private fun fetchMovie(flag: Boolean = false, id: Int = -1){
        if(!flag) _movieViewState.value = MovieNetworkScreenState.ShowListOfMovie("qwerty")
        else if (id!=-1){
            _movieViewState.value = MovieNetworkScreenState.GoDetails(id)
        }
        else
            _movieViewState.value = MovieNetworkScreenState.ShowError(error = "asd")
    }
}