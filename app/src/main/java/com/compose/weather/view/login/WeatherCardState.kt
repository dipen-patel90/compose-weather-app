package com.compose.weather.view.login

import com.compose.weather.model.view.CityWeather

sealed class WeatherCardState {
    object Default : WeatherCardState()
    object Loading : WeatherCardState()
    class Success(val response: CityWeather) : WeatherCardState()
    class Failure(val errorMessage: String) : WeatherCardState()
}
