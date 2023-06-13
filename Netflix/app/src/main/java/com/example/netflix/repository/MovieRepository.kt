package com.example.netflix.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.netflix.Room.MainDB
import com.example.netflix.models.Movie
import com.example.netflix.paging.MoviesPagingSource
import com.example.netflix.request.Service
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

const val NETWORK_PAGE_SIZE = 25

class MovieRepository(private var service: Service): IMovieRepository {

    private lateinit var db: MainDB

    override fun getPagedPopularMovie(): Flow<PagingData<Movie>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { MoviesPagingSource(service) }
        ).flow
    }

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