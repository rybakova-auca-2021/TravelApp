package com.example.travelapp.data.repository

import com.example.travelapp.data.model.LoginUserResponse
import com.example.travelapp.data.model.RegisterUserResponse
import retrofit2.Response

interface AuthRepository {

    suspend fun login(email: String, password: String) : Response<LoginUserResponse>
    suspend fun register(email: String, password: String, repeatedPassword: String) : Response<RegisterUserResponse>
    suspend fun createNewPassword(password: String, newPassword: String, email: String) : Response<Unit>
    suspend fun resetPassword(email: String) : Response<Unit>
    suspend fun verifyCode(email: String, code: String) : Response<Unit>
}