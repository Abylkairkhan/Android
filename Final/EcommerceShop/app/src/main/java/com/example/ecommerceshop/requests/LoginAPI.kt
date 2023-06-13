package com.example.ecommerceshop.requests

import com.example.ecommerceshop.models.Token
import com.example.ecommerceshop.models.UserLogin
import com.example.ecommerceshop.models.UserRegister
import retrofit2.http.Body
import retrofit2.http.POST

interface Login {

    @POST("token/")
    suspend fun login(@Body userLogin: UserLogin): Token

    @POST("register/")
    suspend fun signup(@Body userRegister: UserRegister): UserRegister

}