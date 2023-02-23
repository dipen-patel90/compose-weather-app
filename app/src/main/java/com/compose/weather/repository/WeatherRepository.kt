package com.compose.weather.repository

import com.compose.weather.model.dto.CityWeatherResponse
import retrofit2.Response

interface WeatherRepository {

    suspend fun getCityWeather(
        city: String,
    ): Response<CityWeatherResponse>

    suspend fun getWeatherDetail(
        lat: Float,
        lon: Float
    ): Response<Unit>
}