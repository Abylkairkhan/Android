package com.example.netflix_compose.screens.DetailsScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.netflix.models.Movie
import com.example.netflix_compose.EventHandler
import com.example.netflix_compose.models.Cast
import com.example.netflix_compose.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val repository: IMovieRepository
) : ViewModel(), EventHandler<DetailEvent> {

    private var _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Empty)
    var state = _state.asStateFlow()

    override fun obtainEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.WaitMovie -> showMovie(event.movie_id, event.type)
            is DetailEvent.SaveMovie -> saveMovie(event.movie)
            is DetailEvent.NavigateBack -> navigateBack(event.navController)
            is DetailEvent.DeleteMovie -> repository.deleteSavedMovieByID(event.movie.id!!)
//            is DetailEvent.WaitCast -> showCast(event.movie_id, event.type)
        }
    }

//    private fun showCast(movieId: Int, type: Boolean) {
//        if (!type) {
//            try {
//                viewModelScope.launch {
//                    val movie = repository.getCastByID(movieId)
//
//                    withContext(Dispatchers.IO) {
//                        _state.value = DetailState.ShowMovie(movie, )
//                    }
//                }
//            } catch (e: Exception) {
//                _state.value = DetailState.ShowError(e)
//            }
//        }
//        else _state.value = DetailState.ShowMovie(repository.getSavedMovieByID(movieId))
//    }

    private fun navigateBack(navController: NavController) {
        navController.popBackStack()
    }

    private fun saveMovie(movie: Movie) {
        repository.insertMovieToDatabase(movie)
    }

    private fun showMovie(id: Int, type: Boolean) {

        if (_state.value != DetailState.ShowProgress)
            _state.value = DetailState.ShowProgress
        var movie: Movie
        var cast: List<Cast>
        if (!type) {
            try {
                viewModelScope.launch {
                    movie = repository.getMovieByID(id)
                    cast = repository.getCastByID(id)

                    withContext(Dispatchers.IO) {
                        _state.value = DetailState.ShowMovie(movie, cast)
                    }
                }
            } catch (e: Exception) {
                _state.value = DetailState.ShowError(e)
            }
        }
        else _state.value = DetailState.ShowMovie(repository.getSavedMovieByID(id), null)

    }
}