package com.example.netflix.request

import com.example.netflix.models.Movie
import com.example.netflix.utils.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service(retrofit: Retrofit) {

    private val movieAPI: MovieAPI = retrofit.create(MovieAPI::class.java)

    suspend fun getMovieByID(id: Int): Movie {
        val movie = movieAPI.getMovieByID(id)
        return movie
    }

    suspend fun getData(page: Int): List<Movie> {
        val movies = movieAPI.getListOfMovies(page)
        return movies.results
    }
}