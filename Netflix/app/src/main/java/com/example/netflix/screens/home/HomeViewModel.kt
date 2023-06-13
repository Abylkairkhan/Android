package com.example.netflix.screens.home


import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.netflix.models.Movie
import com.example.netflix.repository.IMovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel(private val movieRepository: IMovieRepository): ViewModel() {

    var movies = MutableLiveData<List<Movie>>();
    val movieFlow: Flow<PagingData<Movie>>

   init {
       movieFlow = movieRepository.getPagedPopularMovie().cachedIn(viewModelScope)
//       movieFlow = loading.asFlow()
//           .flatMapLatest {
//               movieRepository.getPagedPopularMovie()
//           }
//           .cachedIn(viewModelScope)
   }

//    fun getMovies(page: Int) {
//        loading.value = true
//        CoroutineScope(Dispatchers.IO).launch {
//            movies.postValue(movieRepository.getPopularMovie(page))
//            loading.postValue(false)
//        }
//    }

    fun updateMovies(page: Int){
        val currentList = movies.value?.toMutableList() ?: mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            currentList.addAll(movieRepository.getPopularMovie(page))
            movies.postValue(currentList)
        }
    }
}