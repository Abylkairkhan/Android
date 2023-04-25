package com.example.netflix.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.netflix.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MainDB: RoomDatabase() {

    abstract fun getDao(): Dao

    companion object{
        fun getDb(context: Context): MainDB{
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "Movie"
            ).build()
        }
    }
}