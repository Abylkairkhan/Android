package com.example.movieapp.Model

import com.example.movieapp.Model.data.ListMovie
import com.example.movieapp.Model.data.Movie
import retrofit2.http.GET
import retrofit2.http.Path

const val API_KEY = "b6a64ca4504ac701ebc9d76689850386"

interface SereverAPI {

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun getMovieByID(@Path("id") id: Int): Movie

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getListOfMovies(): ListMovie
}