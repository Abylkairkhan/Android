package com.example.netflix.models

data class MoviePopular(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)