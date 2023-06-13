package com.example.ecommerceshop.models


import androidx.annotation.Nullable

data class Book(
    val id: Int,
    var author: Author,
    var genres: List<Genre>,
    val title: String,
    val description: String,
    val image: String,
    val year_of_publication: Int,
    val num_of_pages: Int,
    val rating_count: Int,
    val rating_value: String,
)