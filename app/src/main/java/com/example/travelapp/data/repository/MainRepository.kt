package com.example.travelapp.data.repository

import com.example.travelapp.data.model.PlaceModel
import retrofit2.Response

interface MainRepository {
    suspend fun getPopularList() : Response<List<PlaceModel>>
    suspend fun getMustVisitList() : Response<List<PlaceModel>>
    suspend fun getPackages() : Response<List<PlaceModel>>
}