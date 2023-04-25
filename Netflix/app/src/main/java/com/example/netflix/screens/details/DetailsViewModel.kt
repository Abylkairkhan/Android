package com.example.netflix.screens.details

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.netflix.R
import com.example.netflix.Room.MainDB
import com.example.netflix.models.Movie
import com.example.netflix.request.Service
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel: ViewModel() {

    private val service = Service()
    private lateinit var db: MainDB
    companion object{
        var movie: MutableLiveData<Movie> = MutableLiveData<Movie>()
        var loading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
        var type: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    }

    fun getMovie(): MutableLiveData<Movie>{
        return movie
    }

    fun getLoading(): MutableLiveData<Boolean>{
        return loading
    }

    fun getType(): MutableLiveData<Boolean>{
        return type
    }

    fun saveMovie(context: Context) {
        db = MainDB.getDb(context)
        Thread{
            db.getDao().insert(movie.value!!)
        }.start()
    }

    fun deleteMovie(context: Context) {
        db = MainDB.getDb(context)
        Thread{
            db.getDao().deleteMovieByID(movie.value?.id!!)
        }.start()
    }

    fun passMovie(id: Int){
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val data = service.getMovieByID(id)
            movie.postValue(data)
            loading.postValue(false)
        }
    }

    fun passMovie(_movie: Movie){
        movie.value = _movie
    }

    fun passType(t: Boolean){
        type.value = t
    }
}