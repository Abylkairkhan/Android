package com.example.netflix.di

import com.example.netflix.repository.IMovieRepository
import com.example.netflix.repository.MovieRepository
import com.example.netflix.request.Service
import com.example.netflix.screens.details.DetailsViewModel
import com.example.netflix.screens.home.HomeViewModel
import com.example.netflix.screens.saved.SavedViewModel
import com.example.netflix.utils.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<Service> {
        Service(get())
    }
    single <IMovieRepository>{
        MovieRepository(get())
    }
    single<OkHttpClient> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .client( get() )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DetailsViewModel(get())
    }

    viewModel {
        SavedViewModel(get())
    }
}