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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.weather.R
import com.compose.weather.common.getStateValue
import com.compose.weather.model.ItemCity
import com.compose.weather.view.common.CityGridItem
import com.compose.weather.view.common.Loader
import com.compose.weather.view.login.WeatherCardState
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
            CityWeatherCard(
                state = getStateValue(state = vm.currentCityWeather),
                isCurrentLocation = true
            )
        } else {
            EmptyWeatherCard {
                cameraPermissionState.launchPermissionRequest()
            }
        }

        CityGrid(getStateValue(state = vm.cityList)) {
            vm.setCitySelected(it)
        }

        CityWeatherCard(state = getStateValue(state = vm.cityWeather), isCurrentLocation = false)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CityGrid(cityList: List<ItemCity>, onItemClick: (city: ItemCity) -> Unit) {
    Card(Modifier.padding(12.dp), elevation = 4.dp) {
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
private fun CityWeatherCard(state: WeatherCardState, isCurrentLocation: Boolean) {
    when (state) {
        WeatherCardState.Default -> {}
        is WeatherCardState.Failure -> {
            val message = state.errorMessage
            Text(text = message)
        }
        WeatherCardState.Loading -> Loader(fixHeight = true)
        is WeatherCardState.Success -> {
            state.response.let { cityWeather ->
                Card(
                    Modifier
                        .padding(12.dp)
                        .fillMaxWidth(),
                    elevation = 4.dp
                ) {

                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        val (cityCountry, yourLocation, weather, weatherDesc, infoRow, icon) = createRefs()

                        Text(
                            text = cityWeather.location,
                            modifier = Modifier.constrainAs(cityCountry) {
                                end.linkTo(yourLocation.end)
                                start.linkTo(yourLocation.start)
                            })

                        if (isCurrentLocation) {
                            Text(
                                text = stringResource(id = R.string.your_location),
                                modifier = Modifier.constrainAs(yourLocation) {
                                    top.linkTo(cityCountry.bottom)
                                    start.linkTo(parent.start)
                                })
                        }

                        CoilImage(modifier = Modifier.constrainAs(icon) {
                            end.linkTo(weather.start)
                        }, imageModel = { cityWeather.icon })

                        Text(text = cityWeather.weather, modifier = Modifier.constrainAs(weather) {
                            end.linkTo(weatherDesc.end)
                            start.linkTo(weatherDesc.start)
                        })

                        Text(
                            text = cityWeather.weatherDescription,
                            modifier = Modifier.constrainAs(weatherDesc) {
                                top.linkTo(weather.bottom)
                                end.linkTo(parent.end)
                            })

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .constrainAs(infoRow) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(weatherDesc.bottom)
                                },
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            TwoText(
                                stringResource(id = R.string.temperature),
                                cityWeather.temperature
                            )
                            TwoText(stringResource(id = R.string.sea_level), cityWeather.seaLevel)
                            TwoText(stringResource(id = R.string.wind_speed), cityWeather.windSpeed)
                        }
                    }
                }
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
                Text(text = stringResource(R.string.grant_permission))
                Text(text = stringResource(R.string.press_to_grant_permission))
            }
        }
    }
}

@Composable
private fun TwoText(header: String, value: String) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .defaultMinSize(100.dp, 100.dp),
        elevation = 2.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = header)
            Text(text = value)
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun Preview() {

}