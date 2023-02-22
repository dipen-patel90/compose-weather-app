package com.compose.weather.api

import com.compose.weather.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    //    @GET(".") //If our base URL is same as final URL
    @GET("weather")
    suspend fun getWeatherDetail(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("appid") appid: String = BuildConfig.WEATHER_API_KEY
    ): Response<Unit>
}