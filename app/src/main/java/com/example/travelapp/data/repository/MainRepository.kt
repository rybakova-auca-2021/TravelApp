package com.example.travelapp.data.repository

import com.example.travelapp.data.model.PlaceModel
import retrofit2.Call
import retrofit2.Response

interface MainRepository {
    suspend fun getPopularList() : Call<List<PlaceModel>>
    suspend fun getMustVisitList() : Call<List<PlaceModel>>
    suspend fun getPackages() : Call<List<PlaceModel>>
    suspend fun getPopularPlaceDetail(id: Int) : Call<PlaceModel>
    suspend fun getMustVisitPlaceDetail(id: Int) : Call<PlaceModel>
    suspend fun getPackageDetail(id: Int) : Call<PlaceModel>
}