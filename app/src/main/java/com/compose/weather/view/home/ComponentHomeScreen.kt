package com.compose.weather.view.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.weather.common.getStateValue
import com.compose.weather.model.ItemCity
import com.compose.weather.view.common.CityGridItem
import com.compose.weather.viewmodel.HomeScreenViewModel

@Composable
fun ComponentHomeScreen(username: String, vm: HomeScreenViewModel = hiltViewModel()) {

    CityGrid(getStateValue(state = vm.cityList)) {
        vm.setCitySelected(it)
    }
}

@Composable
private fun CityGrid(cityList: List<ItemCity>, onItemClick: (city: ItemCity) -> Unit) {
    Card(Modifier.padding(12.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(80.dp),
            contentPadding = PaddingValues(10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            items(cityList) {
                CityGridItem(city = it) {
                    onItemClick(it)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    CityGrid(
        listOf(
            ItemCity("Delhi"),
            ItemCity("New York"),
            ItemCity("Mexico City"),
            ItemCity("Moscow"),
            ItemCity("Paris"),
            ItemCity("London"),
            ItemCity("Hong Kong"),
            ItemCity("Toronto"),
            ItemCity("Singapore"),
            ItemCity("Zhengzhou"),
            ItemCity("Busan"),
            ItemCity("Seoul")
        )
    ) {}
}