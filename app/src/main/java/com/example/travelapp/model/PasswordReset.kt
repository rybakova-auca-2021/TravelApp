package com.example.travelapp.model

data class PasswordReset(
    val email: String
)

data class CodeVerificationModel(
    val verification_code: String,
    val email: String
)

data class NewPasswordModel(
    val password: String,
    val new_password: String,
    val email: String
)