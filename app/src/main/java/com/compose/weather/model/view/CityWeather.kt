package com.compose.weather.model.view

data class CityWeather(
    val location: String,
    val city: String,
    val country: String,
    val lat: String,
    val lon: String,
    val weather: String,
    val weatherDescription: String,
    val temperature: String,
    val seaLevel: String,
    val windSpeed: String,
    val date: String,
    val icon: String,
)
