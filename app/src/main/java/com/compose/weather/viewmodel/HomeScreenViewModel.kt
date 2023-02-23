package com.compose.weather.viewmodel

import com.compose.weather.model.ItemCity
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
    }
}