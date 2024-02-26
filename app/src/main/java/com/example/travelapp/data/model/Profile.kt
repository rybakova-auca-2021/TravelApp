package com.example.travelapp.data.model

data class GetProfile (
    val id: Int,
    val username: String,
    val email: String,
    val first_name: String,
    val last_name: String,
    val profile: Profile
)

data class Profile(
    val location: String,
    val phone_number: String
)

data class EditProfile (
    val first_name: String,
    val last_name: String,
    val email: String,
    val location: String,
    val phone_number: String
)

data class EditProfileResponse (
    val id: Int,
    val first_name: String,
    val last_name: String,
    val username: String,
    val email: String,
)