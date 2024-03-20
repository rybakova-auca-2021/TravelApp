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

class GetPopularDetailViewModel(private val repository: MainRepository) : ViewModel() {
    private val _result = MutableLiveData<Result<PlaceModel>>()
    val result: LiveData<Result<PlaceModel>>
        get() = _result

    fun getPopularPlaces(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getPopularPlaceDetail(id)
                response.enqueue(object : Callback<PlaceModel> {
                    override fun onResponse(call: Call<PlaceModel>, response: Response<PlaceModel>) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) {
                                _result.postValue(Result.success(body))
                            } else {
                                _result.postValue(Result.failure(Throwable("Response body is null")))
                            }
                        } else {
                            _result.postValue(Result.failure(Throwable("Failed to get the detail of popular places")))
                        }
                    }

                    override fun onFailure(call: Call<PlaceModel>, t: Throwable) {
                        _result.postValue(Result.failure(t))
                    }
                })
            } catch (e: Exception) {
                _result.postValue(Result.failure(e))
            }
        }
    }
}