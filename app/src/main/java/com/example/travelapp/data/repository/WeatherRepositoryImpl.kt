package com.example.travelapp.data.repository

import com.example.travelapp.data.api.WeatherInterface
import com.example.travelapp.data.model.WeatherResponse
import retrofit2.Call

class WeatherRepositoryImpl(private val weatherInterface: WeatherInterface) : WeatherRepository {
    override suspend fun getCurrentWeather(city: String, apiKey: String): Call<WeatherResponse> {
        return weatherInterface.getCurrentWeather(city, apiKey)
    }
}