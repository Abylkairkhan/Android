package com.example.sneakerstreet.util.event

interface EventHandler<T> {
    fun obtainEvent(event: T)
}