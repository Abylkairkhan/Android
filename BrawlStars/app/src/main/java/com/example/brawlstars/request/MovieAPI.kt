package com.example.brawlstars.request

import com.example.brawlstars.models.Movies
import com.example.brawlstars.models.popularMovies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val API_KEY = "b6a64ca4504ac701ebc9d76689850386"

interface MovieAPI {

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getListOfMovies(@Query("page") page: Int): popularMovies

    @GET("movie/{id}?api_key=${API_KEY}")
    suspend fun getMovieByID(@Path("id") id: Int): Movies
}