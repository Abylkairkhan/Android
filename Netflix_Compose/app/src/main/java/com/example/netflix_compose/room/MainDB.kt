package com.example.netflix.Room

import android.content.Context
import androidx.room.*
import com.example.netflix.models.Movie
import com.example.netflix_compose.room.Converters

@Database(entities = [Movie::class], version = 1)
@TypeConverters(Converters::class)
abstract class MainDB: RoomDatabase() {

    abstract fun getDao(): Dao

//    companion object{
//        fun getDb(context: Context): MainDB{
//            return Room.databaseBuilder(
//                context.applicationContext,
//                MainDB::class.java,
//                "Movie"
//            ).build()
//        }
//    }
}