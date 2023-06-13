package com.example.netflix.screens.details

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.netflix.utils.Result
import com.example.netflix.Room.MainDB
import com.example.netflix.models.Movie
import com.example.netflix.repository.IMovieRepository
import com.example.netflix.utils.SuccessResult
import com.example.netflix.utils.takeSuccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(private val movieRepository: IMovieRepository): ViewModel() {

    private lateinit var db: MainDB
    companion object{
        var movie: MutableLiveData<com.example.netflix.utils.Result<Movie>> = MutableLiveData<com.example.netflix.utils.Result<Movie>>()
        var type: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    }

    fun getMovie(): MutableLiveData<com.example.netflix.utils.Result<Movie>>{
        return movie
    }

    fun getType(): MutableLiveData<Boolean>{
        return type
    }

    fun saveMovie(context: Context) {
        db = MainDB.getDb(context)
        Thread{
            db.getDao().insert(movie.value.takeSuccess()!!)
        }.start()
    }

    fun deleteMovie(context: Context) {
        db = MainDB.getDb(context)
        Thread{
            db.getDao().deleteMovieByID(movie.value.takeSuccess()!!.id!!)
        }.start()
    }

    fun passMovie(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            movie.postValue( SuccessResult(movieRepository.getMovieByID(id) ))
        }
    }

    fun passMovie(_movie: Movie){
        movie.value = SuccessResult(_movie)
    }

    fun passType(t: Boolean){
        type.value = t
    }
}