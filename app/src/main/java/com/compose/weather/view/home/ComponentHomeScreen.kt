package com.compose.weather.view.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.weather.common.getStateValue
import com.compose.weather.model.ItemCity
import com.compose.weather.model.view.CityWeather
import com.compose.weather.view.common.CityGridItem
import com.compose.weather.viewmodel.HomeScreenViewModel
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun ComponentHomeScreen(username: String, vm: HomeScreenViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {

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

    CityWeatherCard(
        CityWeather(
            city = "London",
            lat = "0.1f",
            lon = "0.2f",
            weather = "Cold",
            weatherDescription = "Feels like cold",
            icon = "04d"
        )
    )
}