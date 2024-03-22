package com.example.travelapp.data.repository

import com.example.travelapp.data.model.WeatherResponse
import retrofit2.Call

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String, apiKey: String) : Call<WeatherResponse>
}