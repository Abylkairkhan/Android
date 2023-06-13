package com.example.ecommerceshop.requests

import com.example.ecommerceshop.models.Raiting
import com.example.ecommerceshop.models.Book
import com.example.ecommerceshop.models.Genre
import com.example.ecommerceshop.models.UserData
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BookGenreAPI {

    @GET("genres/")
    suspend fun getGenre(@Header("Authorization") token: String ): List<Genre>

    @GET("books/")
    suspend fun getBook(@Header("Authorization") token: String ): List<Book>

    @GET("library/{id}/books")
    suspend fun getBook2(@Header("Authorization") token: String , @Path("id") id: String): List<Book>

    @GET("books/filtername")
    suspend fun getBookByName(@Header("Authorization") token: String ): List<Book>

    @GET("books/filterpage")
    suspend fun getBookByPage(@Header("Authorization") token: String ): List<Book>

    @GET("books/filteryear")
    suspend fun getBookByYear(@Header("Authorization") token: String ): List<Book>

    @GET("books/filterrating")
    suspend fun getBookByRating(@Header("Authorization") token: String ): List<Book>

    @GET("books/search/{pattern}")
    suspend fun getBookByPattern(
        @Header("Authorization") token: String,
        @Path("pattern") pattern: String): List<Book>

    @GET("ratings/")
    suspend fun getAllRaitings(@Header("Authorization") token: String) : List<Raiting>

    @POST("ratings/")
    suspend fun putRating(@Header("Authorization") token: String, @Body rating: Raiting): Raiting

    @POST("library/{user_id}/books/{book_id}/")
    suspend fun saveBook(@Header("Authorization") token: String, @Path("user_id") user_id: String, @Path("book_id") book_id: String)

    @GET("library/{user_id}/books/")
    suspend fun getBooks(@Header("Authorization") token: String, @Path("user_id") user_id: String): List<Book>

    @GET("register/{pattern}/")
    suspend fun getUserInfo(@Header("Authorization") token: String, @Path("pattern") pattern: String): UserData
}