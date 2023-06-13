package com.example.brawlstars.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.brawlstars.models.Movies

@Database(entities = [Movies::class], version = 1)
abstract class maindb: RoomDatabase() {

    abstract fun getDao(): dao

    companion object{
        fun getdb(context: Context): maindb{
            return Room.databaseBuilder(context.applicationContext, maindb::class.java,"movie_table").build()
        }
    }
}