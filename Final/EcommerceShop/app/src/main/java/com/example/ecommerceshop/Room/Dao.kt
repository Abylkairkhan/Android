package com.example.ecommerceshop.Room

import androidx.room.Insert
import androidx.room.Query
import com.example.ecommerceshop.models.Book

@androidx.room.Dao
interface Dao {
    @Insert
    fun insert(book: Book)

    @Query("SELECT * FROM book_table")
    fun getMovies(): List<Book>

    @Query("DELETE FROM book_table WHERE id = :id")
    fun deleteMovieByID(id: Int)
}