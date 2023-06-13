package com.example.ecommerceshop.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecommerceshop.models.Book


abstract class MainDB: RoomDatabase(){

    abstract fun getDao(): Dao

    companion object{
        fun getDb(context: Context): MainDB{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "Book"
            ).build()
        }
    }
}