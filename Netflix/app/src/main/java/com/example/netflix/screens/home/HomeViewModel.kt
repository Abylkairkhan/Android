package com.example.netflix.screens.home


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.netflix.models.Movie
import com.example.netflix.request.Service
import com.example.netflix.screens.details.DetailsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private var service = Service()
    var movies: MutableLiveData<List<Movie>> = MutableLiveData<List<Movie>>();
    var loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>();

    fun getMovies(page: Int) {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val data = service.getData(page)
            movies.postValue(data)
            loading.postValue(false)
        }
    }

    fun updateMovies(page: Int){
        val currentList = movies.value?.toMutableList() ?: mutableListOf()
        CoroutineScope(Dispatchers.IO).launch {
            currentList.addAll(service.getData(page))
            movies.postValue(currentList)
        }
    }
}