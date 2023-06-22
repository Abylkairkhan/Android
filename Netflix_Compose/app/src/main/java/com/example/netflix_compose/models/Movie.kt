package com.example.netflix.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.netflix_compose.models.Genre

@Entity(tableName = "movie_table")
data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    @PrimaryKey()
    val id: Int? = null,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val genres: List<Genre>,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val original_title: String,
    val vote_average: Double
)