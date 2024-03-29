package com.example.netflix_compose.request

import com.example.netflix.models.Movie
import com.example.netflix.models.MoviePopular
import com.example.netflix_compose.Credentials
import com.example.netflix_compose.models.Cast
import com.example.netflix_compose.models.Credits
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular?api_key=${Credentials.API_KEY}")
    suspend fun getListOfMovies(@Query("page") page: Int): MoviePopular

    @GET("movie/{id}?api_key=${Credentials.API_KEY}")
    suspend fun getMovieByID(@Path("id") id: Int): Movie

    @GET("movie/{id}/credits?api_key=${Credentials.API_KEY}")
    suspend fun getCharacters( @Path("id")id: Int): Credits

}