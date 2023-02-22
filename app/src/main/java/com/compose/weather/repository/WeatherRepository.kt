package com.compose.weather.repository

import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherDetail(
        lat: Float,
        lon: Float
    ): Response<Unit>
}