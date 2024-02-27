package com.example.travelapp.presentation.viewModel.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.model.LoginUserResponse
import com.example.travelapp.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _result = MutableLiveData<Result<LoginUserResponse>>()
    val result: LiveData<Result<LoginUserResponse>>
        get() = _result

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.login(email, password)
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