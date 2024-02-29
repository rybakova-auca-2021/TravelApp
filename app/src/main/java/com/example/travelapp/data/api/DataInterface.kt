package com.example.travelapp.data.api

import com.example.travelapp.data.model.PlaceModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface DataInterface {

    @GET("/places/popular-places/")
    fun getPopularPlaces() : Call<List<PlaceModel>>

    @GET("/places/must-visit-places/")
    fun getMustVisitPlaces() : Call<List<PlaceModel>>

    @GET("/places/packages/")
    fun getPackagesPlaces() : Call<List<PlaceModel>>
}