package com.example.travelapp.api

import com.example.travelapp.model.CodeVerificationModel
import com.example.travelapp.model.LoginUser
import com.example.travelapp.model.LoginUserResponse
import com.example.travelapp.model.NewPasswordModel
import com.example.travelapp.model.PasswordReset
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

    @POST("/api/password-reset/")
    fun resetPassword(
        @Body request: PasswordReset
    ) : Call<Unit>

    @POST("/api/verify-code/")
    fun codeVerification(
        @Body request: CodeVerificationModel
    ) : Call<Unit>

    @POST("/api/create-new-password/")
    fun newPasswordCreate(
        @Body request: NewPasswordModel
    ) : Call<Unit>
}