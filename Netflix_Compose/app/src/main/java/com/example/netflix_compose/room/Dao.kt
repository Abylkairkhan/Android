package com.example.netflix.Room

import androidx.room.Insert
import androidx.room.Query
import com.example.netflix.models.Movie

@androidx.room.Dao
interface Dao {

    @Insert
    fun insert(movie: Movie)

    @Query("SELECT * FROM movie_table")
    fun getMovies(): List<Movie>

    @Query("DELETE FROM movie_table WHERE id = :id")
    fun deleteMovieByID(id: Int)

}