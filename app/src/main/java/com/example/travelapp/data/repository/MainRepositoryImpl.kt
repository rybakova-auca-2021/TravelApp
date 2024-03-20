package com.example.travelapp.data.repository

import com.example.travelapp.data.api.DataInterface
import com.example.travelapp.data.model.PlaceModel
import retrofit2.Call
import retrofit2.Response

class MainRepositoryImpl(private val dataInterface: DataInterface) : MainRepository {
    override suspend fun getPopularList(): Call<List<PlaceModel>> {
        return dataInterface.getPopularPlaces()
    }

    override suspend fun getMustVisitList(): Call<List<PlaceModel>>  {
        return dataInterface.getMustVisitPlaces()
    }

    override suspend fun getPackages(): Call<List<PlaceModel>>  {
        return dataInterface.getPackagesPlaces()
    }

    override suspend fun getPopularPlaceDetail(id: Int): Call<PlaceModel> {
        return dataInterface.getPopularPlaceDetail(id)
    }

    override suspend fun getMustVisitPlaceDetail(id: Int): Call<PlaceModel> {
        return dataInterface.getMustVisitPlaceDetail(id)
    }

    override suspend fun getPackageDetail(id: Int): Call<PlaceModel> {
        return dataInterface.getPackageDetail(id)
    }
}