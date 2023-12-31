package com.example.travelapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.travelapp.api.RetrofitInstance
import com.example.travelapp.model.CodeVerificationModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CodeVerificationViewModel : ViewModel() {
    fun verifyCode(
        code: String,
        email: String,
        onSuccess: () -> Unit,
        onError:() -> Unit
    ) {
        val apiInterface = RetrofitInstance.authApi

        val request = CodeVerificationModel(code, email)
        val call = apiInterface.codeVerification(request)
        call.enqueue(object : Callback<Unit> {
            override fun onResponse(
                call: Call<Unit>,
                response: Response<Unit>
            ) {
                if (response.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onError.invoke()
                    println("Request failed with status code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                println("Request failed: ${t.message}")                }
        })
    }
}