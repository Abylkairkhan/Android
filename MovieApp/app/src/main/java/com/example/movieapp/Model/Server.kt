package com.example.movieapp.Model

import com.example.movieapp.Model.data.Movie
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Server {

    val interceptor = HttpLoggingInterceptor()
    init{
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val serverAPI: SereverAPI = retrofit.create(SereverAPI::class.java)

    suspend fun getMovieByID(id:Int): Movie{
        return serverAPI.getMovieByID(id)
    }

    suspend fun getListOfMovies(): List<Movie>{
        val list = serverAPI.getListOfMovies()
        var listReturn: ArrayList<Movie> = arrayListOf()

        for (item in list.results){
            listReturn.add(item)
        }

        return listReturn
    }
}