package com.example.travelapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.travelapp.api.RetrofitInstance
import com.example.travelapp.model.RegisterUser
import com.example.travelapp.model.RegisterUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    fun register(
        email: String,
        password: String,
        repeatedPassword: String,
        onSuccess: () -> Unit,
        onError:() -> Unit
    ) {
        val apiInterface = RetrofitInstance.authApi

        val request = RegisterUser( email, password, repeatedPassword)
        val call = apiInterface.registerUser(request)
        call.enqueue(object : Callback<RegisterUserResponse> {
            override fun onResponse(
                call: Call<RegisterUserResponse>,
                response: Response<RegisterUserResponse>
            ) {
                if (response.isSuccessful) {
                    onSuccess.invoke()
                    val responseBody = response.body()
                } else {
                    onError.invoke()
                    println("Request failed with status code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<RegisterUserResponse>, t: Throwable) {
                println("Request failed: ${t.message}")                }
        })
    }
}