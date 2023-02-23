package com.compose.weather.viewmodel

import android.util.Log
import com.compose.weather.model.ItemCity
import com.compose.weather.model.dto.toCityWeather
import com.compose.weather.model.view.CityWeather
import com.compose.weather.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    BaseViewModel() {

    private val _cityList = MutableStateFlow(
        listOf(
            ItemCity("Colombo"),
            ItemCity("Delhi", isSelected = true),
            ItemCity("Hong Kong"),
            ItemCity("London"),
            ItemCity("Mexico City"),
            ItemCity("Moscow"),
            ItemCity("New York"),
            ItemCity("Paris"),
            ItemCity("Singapore"),
            ItemCity("Seoul"),
            ItemCity("Toronto"),
            ItemCity("Zhengzhou"),
        )
    )
    val cityList = _cityList.asStateFlow()

    fun setCitySelected(city: ItemCity) {

        _cityList.update {
            it.map {
                it.isSelected = it.city == city.city
                it.copy()
            }
        }

        getCityWeather(city = city.city)
    }


    private val _cityWeather = MutableStateFlow<CityWeather?>(null)
    val cityWeather = _cityWeather.asStateFlow()

    private fun getCityWeather(city: String) = launchWithViewModelScope(
        call = {

            weatherRepository.getCityWeather(city = city).let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {city->
                        _cityWeather.update { city.toCityWeather() }
                    }
                } else {
                    _cityWeather.update { null }
                }
            }
        },
        exceptionCallback = { message ->
            Log.d("CityWeather", "City Weather Exception: $message")
        }
    )

    init {
        _cityList.value.firstOrNull { it.isSelected }?.let {
            getCityWeather(it.city)
        }
    }
}