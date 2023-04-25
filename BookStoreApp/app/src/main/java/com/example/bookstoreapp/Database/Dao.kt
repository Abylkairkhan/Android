package com.example.bookstoreapp.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {
//    User SQL
    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getAllUser(): List<User>

    @Query("SELECT password FROM user_table WHERE username = :username")
    fun getUserPassword(username: String): String

    @Query("SELECT role FROM user_table WHERE username = :username")
    fun getUserRole(username: String): String

    @Query("SELECT username FROM user_table WHERE username = :username")
    fun getUsername(username: String): String


//    Book SQL
    @Insert
    fun insertBook(book: Book)

    @Query("SELECT * FROM book_table")
    fun getAllBook(): List<Book>

    @Query("DELETE FROM book_table WHERE id = :id")
    fun deleteBookByID(id: Int)

    @Query("SELECT * FROM book_table WHERE id = :id")
    fun getBookByID(id: Int): List<Book>

    @Query("SELECT * FROM book_table ORDER BY cost ASC")
    fun getAllBookASC(): List<Book>

    @Query("SELECT * FROM book_table ORDER BY cost DESC")
    fun getAllBookDESC(): List<Book>

    @Query("SELECT * FROM book_table WHERE title LIKE :pattern")
    fun getBookByPattern(pattern: String): List<Book>

    @Query("UPDATE book_table SET title = :title, description = :desc, cost = :cost WHERE id = :id")
    fun updateBook(id: Int, title: String, desc: String, cost: Int)
}