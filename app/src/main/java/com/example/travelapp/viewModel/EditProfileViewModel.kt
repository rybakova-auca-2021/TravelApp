package com.example.travelapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.travelapp.api.RetrofitInstance
import com.example.travelapp.constants.Utils
import com.example.travelapp.model.EditProfile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfileViewModel : ViewModel() {
    fun editUserProfile(
        name: String,
        lastName: String,
        phone: String,
        location: String,
        email: String,
        onSuccess: (EditProfile) -> Unit,
        onError: (String) -> Unit
    ) {
        val apiInterface = RetrofitInstance.profileApi

        val token = Utils.token
        val authHeader = "Bearer $token"
        val request = EditProfile(name, lastName, phone, location, email)

        val call = apiInterface.editProfile(authHeader, request)
        call.enqueue(object : Callback<EditProfile> {
            override fun onResponse(call: Call<EditProfile>, response: Response<EditProfile>) {
                if (response.isSuccessful) {
                    val userProfile = response.body()
                    if (userProfile != null) {
                        onSuccess.invoke(userProfile)
                    }
                } else {
                    onError.invoke("Error fetching user profile")
                }
            }

            override fun onFailure(call: Call<EditProfile>, t: Throwable) {
                onError.invoke("Network error")
            }
        })
    }
}