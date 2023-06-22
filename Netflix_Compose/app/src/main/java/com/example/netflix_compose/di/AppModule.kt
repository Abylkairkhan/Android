package com.example.netflix_compose.di

import androidx.room.Room
import com.example.netflix.Room.Dao
import com.example.netflix.Room.MainDB
import com.example.netflix.request.Service
import com.example.netflix_compose.Credentials
import com.example.netflix_compose.repository.IMovieRepository
import com.example.netflix_compose.repository.MovieRepository
import com.example.netflix_compose.request.MovieAPI
import com.example.netflix_compose.screens.DetailsScreen.DetailViewModel
import com.example.netflix_compose.screens.HomeScreen.HomeViewModel
import com.example.netflix_compose.screens.SavedScreen.SavedViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        get<MainDB>().getDao()
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            MainDB::class.java,
            "Movie"
        ).build()
    }

    viewModel {
        SavedViewModel(get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        DetailViewModel(get())
    }

    factory{
        DetailViewModel(get())
    }

    single<IMovieRepository> {
        MovieRepository(get(), get() as Dao)
    }

    single{
        Service(get())
    }

    single {
        Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}