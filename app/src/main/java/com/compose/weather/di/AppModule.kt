package com.compose.weather.di

import com.compose.weather.api.ApiInterface
import com.compose.weather.repository.WeatherRepository
import com.compose.weather.repository.WeatherRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(apiInterface: ApiInterface): WeatherRepository =
        WeatherRepositoryImpl(apiInterface)
}