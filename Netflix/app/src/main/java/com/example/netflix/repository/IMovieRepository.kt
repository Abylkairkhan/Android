package com.example.netflix.repository

import android.content.Context
import androidx.paging.PagingData
import com.example.netflix.models.Movie
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IMovieRepository {

    fun getPagedPopularMovie(): Flow<PagingData<Movie>>

    suspend fun getPopularMovie(page: Int): List<Movie>

    suspend fun getMovieByID(id: Int): Movie

    fun getSavedMovie(context: Context): List<Movie>

    fun getSavedMovieByID(id: Int): Movie

}