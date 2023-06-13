package com.example.brawlstars.request

import com.example.brawlstars.models.Movies
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.themoviedb.org/3/"

class service {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieAPI: MovieAPI = retrofit.create(MovieAPI::class.java)

    private fun client(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return client
    }

    suspend fun getMovieByID(id: Int): Movies {
        val movie = movieAPI.getMovieByID(id)
        return movie
    }

    suspend fun getData(page: Int): List<Movies> {
        val movies = movieAPI.getListOfMovies(page)
        return movies.results
    }
}