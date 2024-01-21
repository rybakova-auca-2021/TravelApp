package com.example.travelapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.travelapp.api.RetrofitInstance
import com.example.travelapp.constants.Utils
import com.example.travelapp.model.LoginUser
import com.example.travelapp.model.LoginUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError:() -> Unit
    ) {
        val apiInterface = RetrofitInstance.authApi

        val request = LoginUser(email, password)
        val call = apiInterface.login(request)
        call.enqueue(object : Callback<LoginUserResponse> {
            override fun onResponse(
                call: Call<LoginUserResponse>,
                response: Response<LoginUserResponse>
            ) {
                if (response.isSuccessful) {
                    onSuccess.invoke()
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Utils.token = responseBody.access_token
                    }
                } else {
                    onError.invoke()
                    println("Request failed with status code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginUserResponse>, t: Throwable) {
                println("Request failed: ${t.message}")                }
        })
    }
}