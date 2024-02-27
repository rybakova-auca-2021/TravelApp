package com.example.travelapp.presentation.viewModel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.model.RegisterUserResponse
import com.example.travelapp.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _result = MutableLiveData<Result<RegisterUserResponse>>()
    val result: LiveData<Result<RegisterUserResponse>>
        get() = _result


    fun register(
        email: String,
        password: String,
        repeatedPassword: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.register(email, password, repeatedPassword)
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        _result.postValue(Result.success(body))
                    } else {
                        _result.postValue(Result.failure(Throwable("Response body is null")))
                    }
                } else {
                    _result.postValue(Result.failure(Throwable("Login failed")))
                }
            } catch (e: Exception) {
                _result.postValue(Result.failure(e))
            }
        }
    }
}