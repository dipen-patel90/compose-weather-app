package com.compose.weather.repository

import com.compose.weather.api.ApiInterface
import com.compose.weather.model.dto.CityWeatherResponse
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) :
    WeatherRepository {
    override suspend fun getCityWeather(city: String): Response<CityWeatherResponse> {
        return apiInterface.getCityWeather(city)
    }

    override suspend fun getWeatherDetail(
        lat: Float,
        lon: Float
    ): Response<Unit> {
        return apiInterface.getWeatherDetail(lat, lon)
    }
}