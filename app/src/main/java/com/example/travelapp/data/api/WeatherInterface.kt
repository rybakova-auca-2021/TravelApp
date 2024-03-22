package com.example.travelapp.data.api

import com.example.travelapp.data.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherInterface {
//    @GET("data/2.5/weather")
//    fun getCurrentWeather(
//        @Query("lat") lat: Double,
//        @Query("lon") lon: Double,
//        @Query("appid") apiKey: String,
//    ): Call<WeatherResponse>

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
    ): Call<WeatherResponse>
}
