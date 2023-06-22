package com.example.netflix_compose.room

import androidx.room.TypeConverter
import com.example.netflix_compose.models.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromGenres(genres: List<Genre>): String {
        val gson = Gson()
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenres(genresString: String): List<Genre> {
        val gson = Gson()
        val type = object : TypeToken<List<Genre>>() {}.type
        return gson.fromJson(genresString, type)
    }

}