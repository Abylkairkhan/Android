package com.example.brawlstars.models

data class popularMovies(
    val page: Int,
    val results: List<Movies>,
    val totalPages: Int,
    val totalResults: Int
)
