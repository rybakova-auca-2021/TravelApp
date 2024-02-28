package com.example.travelapp.data.repository

import com.example.travelapp.data.api.DataInterface
import com.example.travelapp.data.model.PlaceModel
import retrofit2.Response

class MainRepositoryImpl(private val dataInterface: DataInterface) : MainRepository {
    override suspend fun getPopularList(): Response<List<PlaceModel>> {
        return dataInterface.getPopularPlaces()
    }

    override suspend fun getMustVisitList(): Response<List<PlaceModel>>  {
        return dataInterface.getMustVisitPlaces()
    }

    override suspend fun getPackages(): Response<List<PlaceModel>>  {
        return dataInterface.getPackagesPlaces()
    }
}