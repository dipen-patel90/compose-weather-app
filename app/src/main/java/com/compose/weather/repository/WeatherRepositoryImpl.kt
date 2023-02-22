package com.compose.weather.repository

import com.compose.weather.api.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) :
    WeatherRepository {
    override suspend fun getWeatherDetail(
        lat: Float,
        lon: Float
    ): Response<Unit> {
        return apiInterface.getWeatherDetail(lat, lon)
    }
}