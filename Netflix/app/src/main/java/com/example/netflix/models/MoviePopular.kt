package com.example.netflix.models


import com.google.gson.annotations.SerializedName

data class MoviePopular(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)