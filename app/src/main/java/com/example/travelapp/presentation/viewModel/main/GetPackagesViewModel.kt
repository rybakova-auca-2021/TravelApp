package com.example.travelapp.presentation.viewModel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.model.PlaceModel
import com.example.travelapp.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPackagesViewModel(private val repository: MainRepository) : ViewModel() {
    private val _result = MutableLiveData<Result<List<PlaceModel>>>()
    val result: LiveData<Result<List<PlaceModel>>>
        get() = _result

    fun getPackages() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPackages()
                response.enqueue(object : Callback<List<PlaceModel>> {
                    override fun onResponse(call: Call<List<PlaceModel>>, response: Response<List<PlaceModel>>) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) {
                                _result.postValue(Result.success(body))
                            } else {
                                _result.postValue(Result.failure(Throwable("Response body is null")))
                            }
                        } else {
                            _result.postValue(Result.failure(Throwable("Failed to get the list of must visit places")))
                        }
                    }

                    override fun onFailure(call: Call<List<PlaceModel>>, t: Throwable) {
                        _result.postValue(Result.failure(t))
                    }
                })
            } catch (e: Exception) {
                _result.postValue(Result.failure(e))
            }
        }
    }
}