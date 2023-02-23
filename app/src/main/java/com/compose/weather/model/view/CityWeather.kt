package com.compose.weather.model.view

data class CityWeather(
    val city: String,
    val lat: String,
    val lon: String,
    val weather: String,
    val weatherDescription: String,
    val icon: String
)
