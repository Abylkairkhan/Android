package com.example.netflix.request

import com.example.netflix.models.Movie
import com.example.netflix_compose.Credentials
import com.example.netflix_compose.models.Cast
import com.example.netflix_compose.request.MovieAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service(
    private val movieAPI: MovieAPI
) {

//    private val retrofit = Retrofit.Builder()
//        .baseUrl(Credentials.BASE_URL)
//        .client(getClient())
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//    private val movieAPI: MovieAPI = retrofit.create(MovieAPI::class.java)
//
//    private fun getClient(): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        return OkHttpClient.Builder()
//                .addInterceptor(interceptor)
//                .build()
//    }

    suspend fun getCast(id: Int): List<Cast> {
        val castList = movieAPI.getCharacters(id)
        return castList.cast
    }

    suspend fun getMovieByID(id: Int): Movie {
        val movie = movieAPI.getMovieByID(id)
        return movie
    }

    suspend fun getData(page: Int): List<Movie> {
        val movies = movieAPI.getListOfMovies(page)
        return movies.results
    }
}