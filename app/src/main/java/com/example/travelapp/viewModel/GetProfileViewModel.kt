package com.example.travelapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.travelapp.api.RetrofitInstance
import com.example.travelapp.constants.Utils
import com.example.travelapp.model.GetProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetProfileViewModel : ViewModel() {
    fun getUserProfile(onSuccess: (GetProfile) -> Unit, onError: (String) -> Unit) {
        val apiInterface = RetrofitInstance.profileApi

        val token = Utils.token
        val authHeader = "Bearer $token"

        val call = apiInterface.getProfileData(authHeader)
        call.enqueue(object : Callback<GetProfile> {
            override fun onResponse(call: Call<GetProfile>, response: Response<GetProfile>) {
                if (response.isSuccessful) {
                    val userProfile = response.body()
                    if (userProfile != null) {
                        onSuccess.invoke(userProfile)
                    }
                } else {
                    onError.invoke("Error fetching user profile")
                }
            }

            override fun onFailure(call: Call<GetProfile>, t: Throwable) {
                onError.invoke("Network error")
            }
        })
    }
}