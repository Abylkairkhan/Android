package com.example.netflix_compose.screens.SavedScreen

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.netflix_compose.EventHandler
import com.example.netflix_compose.navigation.HomeScreen
import com.example.netflix_compose.repository.IMovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavedViewModel(
    private val repository: IMovieRepository
) : ViewModel(), EventHandler<SavedEvent> {

    private var _state = MutableStateFlow<SavedState>(SavedState.Empty)
    var state = _state.asStateFlow()

    init {
        showMovie()
    }

    override fun obtainEvent(event: SavedEvent) {
        when(event){
            is SavedEvent.GetSavedMovies -> showMovie()
            is SavedEvent.DetailsEvent -> {
                event.navController.navigate(HomeScreen.Detail.route+ '/' + event.movie_id + '/' + event.type)
//                goDetails(event.movie_id, event.navController)
            }
        }
    }

    private fun showMovie(){
        _state.value = SavedState.ShowMovies(repository.getSavedMovie())
    }
}