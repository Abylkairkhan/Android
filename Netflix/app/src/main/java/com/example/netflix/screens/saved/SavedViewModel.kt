package com.example.netflix.screens.saved

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.netflix.models.Movie
import com.example.netflix.repository.IMovieRepository

class SavedViewModel(private var repository: IMovieRepository): ViewModel() {

    fun getSavedMovie(context: Context): List<Movie>{
        return repository.getSavedMovie(context)
    }

    fun passMovie(movie: Movie){
        Log.d("MyLog", "updateMovie")
    }
}