package com.example.netflix.screens.saved

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.netflix.Room.MainDB
import com.example.netflix.models.Movie
import com.example.netflix.screens.details.DetailsViewModel

class SavedViewModel: ViewModel() {

    private lateinit var db: MainDB
    private var dvm = DetailsViewModel()

    fun getSavedMovie(context: Context): List<Movie>{
        var  list = emptyList<Movie>()
        Thread {
            db = MainDB.getDb(context)
            list = db.getDao().getMovies()
        }.start()

        while (list.isEmpty()){
            Thread.sleep(100)
        }

        return list
    }

    fun passMovie(movie: Movie){
        Log.d("MyLog", "updateMovie")
    }
}