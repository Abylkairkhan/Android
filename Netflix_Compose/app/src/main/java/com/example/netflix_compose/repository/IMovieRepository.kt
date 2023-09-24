package com.example.netflix_compose.repository

import android.content.Context
import com.example.netflix.models.Movie
import com.example.netflix_compose.models.Cast

interface IMovieRepository {

    suspend fun getPopularMovie(page: Int): List<Movie>
    suspend fun getCastByID(id: Int): List<Cast>

    suspend fun getMovieByID(id: Int): Movie

    fun getSavedMovie(): List<Movie>

    fun getSavedMovieByID(id: Int): Movie

    fun deleteSavedMovieByID(id: Int)

    fun insertMovieToDatabase(movie: Movie)
}