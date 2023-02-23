package com.compose.weather.view.home

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.weather.common.getStateValue
import com.compose.weather.model.ItemCity
import com.compose.weather.model.view.CityWeather
import com.compose.weather.view.common.CityGridItem
import com.compose.weather.viewmodel.HomeScreenViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.skydoves.landscapist.coil.CoilImage

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ComponentHomeScreen(username: String, vm: HomeScreenViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

        val cameraPermissionState =
            rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)

        if (cameraPermissionState.status.isGranted) {
            vm.getLocation(LocalContext.current)
            CityWeatherCard(getStateValue(state = vm.currentCityWeather))
        } else {
            EmptyWeatherCard {
                cameraPermissionState.launchPermissionRequest()
            }
        }

        CityGrid(getStateValue(state = vm.cityList)) {
            vm.setCitySelected(it)
        }

        CityWeatherCard(getStateValue(state = vm.cityWeather))
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CityGrid(cityList: List<ItemCity>, onItemClick: (city: ItemCity) -> Unit) {
    Card(Modifier.padding(12.dp)) {
        FlowRow(
            maxItemsInEachRow = 4,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            cityList.forEach {
                CityGridItem(city = it) {
                    onItemClick(it)
                }
            }
        }
    }
}

@Composable
private fun CityWeatherCard(cityWeather: CityWeather?) {
    cityWeather?.let {
        Card(
            Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {

            Column(
                Modifier
                    .padding(12.dp)
            ) {
                Text(text = cityWeather.city)
                Text(text = cityWeather.lat)
                Text(text = cityWeather.lon)
                Text(text = cityWeather.weather)
                Text(text = cityWeather.weatherDescription)
                CoilImage(
                    imageModel = { cityWeather.icon }
                )
            }
        }
    }
}

@Composable
private fun EmptyWeatherCard(askForPermission: () -> Unit) {
    Card(
        Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clickable {
                    askForPermission()
                },
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Grant permission to view current location weather.")
                Text(text = "Press to Grant Permission.")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
//    CityGrid(
//        listOf(
//            ItemCity("Delhi"),
//            ItemCity("New York"),
//            ItemCity("Mexico City"),
//            ItemCity("Moscow"),
//            ItemCity("Paris"),
//            ItemCity("London"),
//            ItemCity("Hong Kong"),
//            ItemCity("Toronto"),
//            ItemCity("Singapore"),
//            ItemCity("Zhengzhou"),
//            ItemCity("Busan"),
//            ItemCity("Seoul")
//        )
//    ) {}

//    CityWeatherCard(
//        CityWeather(
//            city = "London",
//            lat = "0.1f",
//            lon = "0.2f",
//            weather = "Cold",
//            weatherDescription = "Feels like cold",
//            icon = "04d"
//        )
//    )

//    EmptyWeatherCard() {}

    ComponentHomeScreen("Dipen", viewModel())
}