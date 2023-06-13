package com.example.brawlstars.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.brawlstars.models.Movies

@Dao
interface dao {
    @Insert
    fun insert(movie: Movies)

    @Query("SELECT * FROM movie_table")
    fun getMovies(): List<Movies>

    @Query("DELETE FROM movie_table WHERE id = :id")
    fun deleteMovieByID(id: Int)
}