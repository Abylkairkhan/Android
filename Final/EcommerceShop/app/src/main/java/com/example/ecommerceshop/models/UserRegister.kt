package com.example.ecommerceshop.models

data class UserRegister(
    val username: String,
    val email: String,
    val password: String,
    val groups: Array<Int>
)
