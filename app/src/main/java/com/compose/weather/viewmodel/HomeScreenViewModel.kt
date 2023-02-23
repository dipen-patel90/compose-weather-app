package com.compose.weather.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.compose.weather.common.LatLon
import com.compose.weather.model.ItemCity
import com.compose.weather.model.dto.toCityWeather
import com.compose.weather.model.view.CityWeather
import com.compose.weather.repository.WeatherRepository
import com.google.android.gms.location.LocationServices
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
                    response.body()?.let { city ->
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

    private var latLon: LatLon? = null

    @SuppressLint("MissingPermission")
    fun getLocation(context: Context) {
        LocationServices.getFusedLocationProviderClient(context).lastLocation.addOnSuccessListener {
            it?.let {
                val newCord = LatLon(it.latitude, it.longitude)

                if (latLon == null || latLon!!.lat != newCord.lat || latLon!!.lon != newCord.lon) {
                    Log.d("getLocation", "getLocation : Calling API")
                    getCurrentCityWeather(newCord)
                }

                latLon = newCord
            }
        }
    }

    private val _currentCityWeather = MutableStateFlow<CityWeather?>(null)
    val currentCityWeather = _currentCityWeather.asStateFlow()

    private fun getCurrentCityWeather(coordinate: LatLon) = launchWithViewModelScope(
        call = {

            weatherRepository.getWeatherDetail(coordinate.lat, coordinate.lon).let { response ->
                if (response.isSuccessful) {
                    response.body()?.let { city ->
                        _currentCityWeather.update { city.toCityWeather() }
                    }
                } else {
                    _currentCityWeather.update { null }
                }
            }
        },
        exceptionCallback = { message ->
            Log.d("CityWeather", "Current City Weather Exception: $message")
        }
    )

    init {
        _cityList.value.firstOrNull { it.isSelected }?.let {
            getCityWeather(it.city)
        }
    }
}