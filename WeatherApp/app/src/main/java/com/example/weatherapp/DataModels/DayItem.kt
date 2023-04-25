package com.example.weatherapp.DataModels

data class DayItem(
    val city: String,
    val country: String,
    val time: String,
    val condition: String,
    val imageURL: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val windMPH: String,
    val hours: String
)
