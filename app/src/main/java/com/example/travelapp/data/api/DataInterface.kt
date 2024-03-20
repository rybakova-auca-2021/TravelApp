package com.example.travelapp.data.api

import com.example.travelapp.data.model.PlaceModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DataInterface {

    @GET("/places/popular-places/")
    fun getPopularPlaces() : Call<List<PlaceModel>>

    @GET("/places/must-visit-places/")
    fun getMustVisitPlaces() : Call<List<PlaceModel>>

    @GET("/places/packages/")
    fun getPackagesPlaces() : Call<List<PlaceModel>>

    @GET("/places/popular-places/{place_id}/")
    fun getPopularPlaceDetail(@Path("place_id") id: Int) : Call<PlaceModel>

    @GET("/places/must-visit-places/{place_id}/")
    fun getMustVisitPlaceDetail(@Path("place_id") id: Int) : Call<PlaceModel>

    @GET("/places/packages/{place_id}/")
    fun getPackageDetail(@Path("place_id") id: Int) : Call<PlaceModel>
}