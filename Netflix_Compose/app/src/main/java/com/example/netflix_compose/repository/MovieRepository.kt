package com.example.netflix_compose.repository

import com.example.netflix.Room.Dao
import com.example.netflix.models.Movie
import com.example.netflix.request.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

const val NETWORK_PAGE_SIZE = 25

class MovieRepository(
    private val service: Service,
    private val database: Dao
): IMovieRepository {

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

    override fun getSavedMovie(): List<Movie> {
        var list = emptyList<Movie>()

        Thread {
            list = database.getMovies()
        }.start()

        while (list.isEmpty()) {
            Thread.sleep(100)
        }

        return list
    }

    override fun getSavedMovieByID(id: Int): Movie {
        var list: Movie? = null

        Thread{
            list = database.getMovieByID(id)
        }.start()

        while (list==null) {
            Thread.sleep(100)
        }

        return list!!
    }

    override fun deleteSavedMovieByID(id: Int) {
        Thread{
            database.deleteMovieByID(id)
        }.start()
    }

    override fun insertMovieToDatabase(movie: Movie) {
        Thread{
            database.insert(movie)
        }.start()
    }
}