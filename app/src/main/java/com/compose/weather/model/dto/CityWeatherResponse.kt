package com.compose.weather.model.dto


import com.compose.weather.common.Constants.IMAGE_URL
import com.compose.weather.common.empty
import com.compose.weather.model.view.CityWeather
import com.google.gson.annotations.SerializedName

data class CityWeatherResponse(
    @SerializedName("base")
    val base: String?,
    @SerializedName("clouds")
    val clouds: Clouds?,
    @SerializedName("cod")
    val cod: Int?,
    @SerializedName("coord")
    val coord: Coord?,
    @SerializedName("dt")
    val dt: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("main")
    val main: Main?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("sys")
    val sys: Sys?,
    @SerializedName("timezone")
    val timezone: Int?,
    @SerializedName("visibility")
    val visibility: Int?,
    @SerializedName("weather")
    val weather: List<Weather?>?,
    @SerializedName("wind")
    val wind: Wind?
) {

    data class Clouds(
        @SerializedName("all")
        val all: Int?
    )

    data class Coord(
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("lon")
        val lon: Double?
    )

    data class Main(
        @SerializedName("feels_like")
        val feelsLike: Double?,
        @SerializedName("humidity")
        val humidity: Int?,
        @SerializedName("pressure")
        val pressure: Int?,
        @SerializedName("temp")
        val temp: Double?,
        @SerializedName("temp_max")
        val tempMax: Double?,
        @SerializedName("temp_min")
        val tempMin: Double?
    )

    data class Sys(
        @SerializedName("country")
        val country: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("sunrise")
        val sunrise: Int?,
        @SerializedName("sunset")
        val sunset: Int?,
        @SerializedName("type")
        val type: Int?
    )

    data class Weather(
        @SerializedName("description")
        val description: String?,
        @SerializedName("icon")
        val icon: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("main")
        val main: String?
    )

    data class Wind(
        @SerializedName("deg")
        val deg: Int?,
        @SerializedName("speed")
        val speed: Double?
    )
}

fun CityWeatherResponse.toCityWeather(): CityWeather {
    val wt = weather?.firstOrNull()

    return CityWeather(
        city = name ?: String.empty(),
        lat = coord?.lat?.toString() ?: String.empty(),
        lon = coord?.lon?.toString() ?: String.empty(),
        weather = wt?.main ?: String.empty(),
        weatherDescription = wt?.description ?: String.empty(),
        icon = "$IMAGE_URL${wt?.icon ?: String.empty()}@2x.png"
    )
}