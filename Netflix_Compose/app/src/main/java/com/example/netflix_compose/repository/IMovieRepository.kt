package com.example.netflix_compose.repository

import android.content.Context
import com.example.netflix.models.Movie

interface IMovieRepository {

    suspend fun getPopularMovie(page: Int): List<Movie>

    suspend fun getMovieByID(id: Int): Movie

    fun getSavedMovie(): List<Movie>

    fun getSavedMovieByID(id: Int): Movie

    fun deleteSavedMovieByID(id: Int)

    fun insertMovieToDatabase(movie: Movie)
}