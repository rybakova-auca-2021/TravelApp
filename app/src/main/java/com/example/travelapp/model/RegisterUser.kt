package com.example.travelapp.model

data class RegisterUser(
    val email: String,
    val password: String,
    val password_confirm: String
)

data class RegisterUserResponse(
    val message: String,
    val access_token: String,
    val refresh_token: String
)