package com.example.netflix_compose.screens.HomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.netflix.models.Movie
import com.example.netflix_compose.EventHandler
import com.example.netflix_compose.navigation.HomeScreen
import com.example.netflix_compose.paging.DefaultPaginator
import com.example.netflix_compose.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val repository: IMovieRepository
) : ViewModel(), EventHandler<HomeEvent> {

    private var _state = MutableStateFlow<HomeState>(HomeState.Empty)
    var state = _state.asStateFlow()
    var page = 1

    init {
        showMovies()
    }

    override fun obtainEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.DetailsEvent -> {
                event.navController.navigate(HomeScreen.Detail.route + '/' + event.movie_id + '/' + event.type)
            }
            is HomeEvent.LoadNextMovies -> {
                page++
                showMovies()
            }
        }
    }

    private fun showMovies() {
        try {
            if (_state.value != HomeState.ShowProgress)
                _state.value = HomeState.ShowProgress

            viewModelScope.launch {
                _state.value = HomeState.ShowMovies(repository.getPopularMovie(page))
            }
        } catch (e: Exception) {
            _state.value = HomeState.ShowError(e)
        }
    }
}
