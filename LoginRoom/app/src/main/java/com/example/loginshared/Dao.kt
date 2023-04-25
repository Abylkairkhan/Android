package com.example.loginshared

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertUser(user: User)

    @Query("SELECT password FROM user_table where username = :name")
    fun checkPassword(name: String): String

    @Query("SELECT * FROM user_table")
    fun getAll(): Flow<List<User>>
}