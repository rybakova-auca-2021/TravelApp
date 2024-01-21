package com.example.travelapp.api

import com.example.travelapp.model.EditProfile
import com.example.travelapp.model.EditProfileResponse
import com.example.travelapp.model.GetProfile
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.PUT
import retrofit2.http.Part

interface ProfileInterface {
    @GET("/profile/")
    fun getProfileData(@Header("Authorization") token: String): Call<GetProfile>

    @PUT("/profile/edit/")
    fun editProfile(@Header("Authorization") token: String, @Body request: EditProfile): Call<EditProfile>

    @Multipart
    @PATCH("/profile_photo/edit/")
    fun editProfilePhoto(
        @Header("Authorization") token: String,
        @Part profile_photo: MultipartBody.Part
    ): Call<Unit>

}