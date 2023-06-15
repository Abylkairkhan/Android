package com.example.netflix_compose.screens.models

interface EventHandler<T> {
    fun obtainEvent(event: T)
}