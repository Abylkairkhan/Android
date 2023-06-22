package com.example.netflix_compose.paging

interface Paginator <Key, Item> {

    suspend fun loadNextItems()
    fun reset()

}