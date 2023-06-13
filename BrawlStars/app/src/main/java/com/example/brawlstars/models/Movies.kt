package com.example.brawlstars.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movies(
    val adult: Boolean,
    val backdrop_path: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val original_language: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val vote_average: Double
)
