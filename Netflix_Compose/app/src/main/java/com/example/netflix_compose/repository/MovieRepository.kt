package com.example.netflix_compose.repository

import android.content.Context
import com.example.netflix.Room.MainDB
import com.example.netflix.models.Movie
import com.example.netflix.request.Service
import com.example.netflix_compose.repository.IMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

const val NETWORK_PAGE_SIZE = 25

class MovieRepository(): IMovieRepository {

    private var service: Service = Service()
    private lateinit var db: MainDB

    override suspend fun getPopularMovie(page: Int): List<Movie> {
        val deferred = GlobalScope.async(Dispatchers.Default) {
            service.getData(page)
        }

        return deferred.await()
    }

    override suspend fun getMovieByID(id: Int): Movie {

        val deferred = GlobalScope.async(Dispatchers.Default) {
            service.getMovieByID(id)
        }

        return deferred.await()
    }

    override fun getSavedMovie(context: Context): List<Movie> {
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

    override fun getSavedMovieByID(id: Int): Movie {
        TODO("Not yet implemented")
    }
}