package com.example.travelapp.data.model

data class LoginUser(
    val email: String,
    val password: String
)
data class LoginUserResponse(
    val message: String,
    val user_id: String,
    val access_token: String,
    val refresh_token: String
)