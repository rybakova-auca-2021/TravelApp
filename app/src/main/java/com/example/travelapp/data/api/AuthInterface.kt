package com.example.travelapp.data.api

import com.example.travelapp.data.model.CodeVerificationModel
import com.example.travelapp.data.model.LoginUser
import com.example.travelapp.data.model.LoginUserResponse
import com.example.travelapp.data.model.NewPasswordModel
import com.example.travelapp.data.model.PasswordReset
import com.example.travelapp.data.model.RegisterUser
import com.example.travelapp.data.model.RegisterUserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthInterface {
    @POST("/auth/signup/")
    fun registerUser(
        @Body request: RegisterUser
    ) : Call<RegisterUserResponse>

    @POST("/auth/login/")
    fun login(
        @Body request: LoginUser
    ) : Call<LoginUserResponse>

    @POST("/auth/password-reset/")
    fun resetPassword(
        @Body request: PasswordReset
    ) : Call<Unit>

    @POST("/auth/verify-code/")
    fun codeVerification(
        @Body request: CodeVerificationModel
    ) : Call<Unit>

    @POST("/auth/create-new-password/")
    fun newPasswordCreate(
        @Body request: NewPasswordModel
    ) : Call<Unit>
}