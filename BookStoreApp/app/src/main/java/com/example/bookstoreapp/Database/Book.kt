package com.example.bookstoreapp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
data class Book(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var desc: String,
    @ColumnInfo(name = "cost")
    var cost: Int
)
