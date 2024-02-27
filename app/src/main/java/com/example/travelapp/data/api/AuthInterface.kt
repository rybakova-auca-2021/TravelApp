package com.example.travelapp.data.api

import com.example.travelapp.data.model.CodeVerificationModel
import com.example.travelapp.data.model.LoginUser
import com.example.travelapp.data.model.LoginUserResponse
import com.example.travelapp.data.model.NewPasswordModel
import com.example.travelapp.data.model.PasswordReset
import com.example.travelapp.data.model.RegisterUser
import com.example.travelapp.data.model.RegisterUserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthInterface {
    @POST("/auth/signup/")
    suspend fun registerUser(
        @Body request: RegisterUser
    ) : Response<RegisterUserResponse>

    @POST("/auth/login/")
    suspend fun login(
        @Body request: LoginUser
    ) : Response<LoginUserResponse>

    @POST("/auth/password-reset/")
    suspend fun resetPassword(
        @Body request: PasswordReset
    ) : Response<Unit>

    @POST("/auth/verify-code/")
    suspend fun codeVerification(
        @Body request: CodeVerificationModel
    ) : Response<Unit>

    @POST("/auth/create-new-password/")
    suspend fun newPasswordCreate(
        @Body request: NewPasswordModel
    ) : Response<Unit>
}