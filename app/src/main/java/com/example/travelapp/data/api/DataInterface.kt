package com.example.travelapp.data.api

import com.example.travelapp.data.model.PlaceModel
import retrofit2.Response
import retrofit2.http.GET

interface DataInterface {

    @GET("places/popular-places/")
    fun getPopularPlaces() : Response<List<PlaceModel>>

    @GET("places/must-visit-places/")
    fun getMustVisitPlaces() : Response<List<PlaceModel>>

    @GET("places/packages/")
    fun getPackagesPlaces() : Response<List<PlaceModel>>
}