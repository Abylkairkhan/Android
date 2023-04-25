package com.example.netflix.request

import android.util.Log
import com.example.netflix.models.Movie
import com.example.netflix.models.MoviePopular
import com.example.netflix.utils.Credentials
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Credentials.BASE_URL)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val movieAPI: MovieAPI = retrofit.create(MovieAPI::class.java)

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return client
    }

    suspend fun getMovieByID(id: Int): Movie{
        val movie = movieAPI.getMovieByID(id)
        return movie
    }

    suspend fun getData(page: Int): List<Movie> {
        val movies = movieAPI.getListOfMovies(page)
        return movies.results
    }
}