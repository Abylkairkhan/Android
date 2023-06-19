package com.example.netflix_compose

interface EventHandler<T> {
    fun obtainEvent(event: T)
}