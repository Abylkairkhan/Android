package com.example.ecommerceshop.requests


import com.example.ecommerceshop.models.Raiting
import android.util.Log
import com.example.ecommerceshop.Credentials
import com.example.ecommerceshop.models.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Service {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Credentials.BASE_URL)
        .client(getClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private val loginAPI: Login = retrofit.create(Login::class.java)
    private val bookGenreAPI: BookGenreAPI = retrofit.create(BookGenreAPI::class.java)

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        return client
    }

    suspend fun getUserInfo(username:String){
        val data = bookGenreAPI.getUserInfo("Bearer "+ Credentials.TOKEN,username)
        Credentials.USER_ID = data.id
        Log.d("MyLog", "${Credentials.USER_ID}")
    }

    suspend fun getBookSaved(user_id: String): List<Book>{
        return bookGenreAPI.getBooks("Bearer "+ Credentials.TOKEN, user_id)
    }

    suspend fun addBook(user_id: String, book_id: String){
        bookGenreAPI.saveBook("Bearer "+ Credentials.TOKEN, user_id, book_id)
    }

    suspend fun putRating(rating: Raiting): Raiting{
        return bookGenreAPI.putRating("Bearer "+ Credentials.TOKEN, rating)
    }

    suspend fun getAllRatings(): List<Raiting>{
        return bookGenreAPI.getAllRaitings("Bearer "+ Credentials.TOKEN)
    }

    suspend fun getGenre(): List<Genre>{
        return bookGenreAPI.getGenre("Bearer "+ Credentials.TOKEN)
    }

    suspend fun getBook(): List<Book>{
        return bookGenreAPI.getBook("Bearer "+ Credentials.TOKEN)
    }

    suspend fun getBook2(): List<Book>{
        return bookGenreAPI.getBook2("Bearer "+ Credentials.TOKEN, Credentials.USER_ID.toString())
    }

    suspend fun getBookByName(): List<Book>{
        return bookGenreAPI.getBookByName("Bearer "+ Credentials.TOKEN)
    }

    suspend fun getBookByPage(): List<Book>{
        return bookGenreAPI.getBookByPage("Bearer "+ Credentials.TOKEN)
    }

    suspend fun getBookByYear(): List<Book>{
        return bookGenreAPI.getBookByYear("Bearer "+ Credentials.TOKEN)
    }

    suspend fun getBookByRating(): List<Book>{
        return bookGenreAPI.getBookByRating("Bearer "+ Credentials.TOKEN)
    }

    suspend fun getBookByPattern(pattern: String): List<Book>{
        return bookGenreAPI.getBookByPattern("Bearer "+ Credentials.TOKEN, pattern)
    }


    suspend fun login(userLogin: UserLogin): Token{
        try {
            val token = loginAPI.login(userLogin)
            return token
        }
        catch(_: Exception){
            return Token("","")
        }
    }

    suspend fun signup(userRegister: UserRegister): UserRegister{
        try {
            val user = loginAPI.signup(userRegister)
            return user
        }
        catch(_: Exception){
            return UserRegister("","","", arrayOf())
        }
    }
}