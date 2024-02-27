package com.example.travelapp.data.repository

import com.example.travelapp.data.api.AuthInterface
import com.example.travelapp.data.model.CodeVerificationModel
import com.example.travelapp.data.model.LoginUser
import com.example.travelapp.data.model.LoginUserResponse
import com.example.travelapp.data.model.NewPasswordModel
import com.example.travelapp.data.model.PasswordReset
import com.example.travelapp.data.model.RegisterUser
import com.example.travelapp.data.model.RegisterUserResponse
import retrofit2.Response

class AuthRepositoryImpl(private val authInterface: AuthInterface) : AuthRepository {
    override suspend fun login(email: String, password: String): Response<LoginUserResponse> {
        val user = LoginUser(email, password)
        return authInterface.login(user)
    }

    override suspend fun register(email: String, password: String, repeatedPassword: String): Response<RegisterUserResponse> {
        val user = RegisterUser(email, password, repeatedPassword)
        return authInterface.registerUser(user)
    }

    override suspend fun createNewPassword(password: String, newPassword: String, email: String): Response<Unit> {
        val model = NewPasswordModel(password, newPassword, email)
        return authInterface.newPasswordCreate(model)
    }

    override suspend fun resetPassword(email: String): Response<Unit> {
        val resetModel = PasswordReset(email)
        return authInterface.resetPassword(resetModel)
    }

    override suspend fun verifyCode(email: String, code: String): Response<Unit> {
        val codeModel = CodeVerificationModel(email, code)
        return authInterface.codeVerification(codeModel)
    }
}