package com.example.travelapp.api

import com.example.travelapp.model.LoginUser
import com.example.travelapp.model.LoginUserResponse
import com.example.travelapp.model.RegisterUser
import com.example.travelapp.model.RegisterUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthInterface {
    @POST("/api/signup/")
    fun registerUser(
        @Body request: RegisterUser
    ) : Call<RegisterUserResponse>

    @POST("/api/login/")
    fun login(
        @Body request: LoginUser
    ) : Call<LoginUserResponse>
}