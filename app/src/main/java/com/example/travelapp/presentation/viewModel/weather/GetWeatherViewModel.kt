package com.example.travelapp.presentation.viewModel.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelapp.data.model.WeatherResponse
import com.example.travelapp.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetWeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _result = MutableLiveData<Result<WeatherResponse>>()
    val result: LiveData<Result<WeatherResponse>>
        get() = _result


    fun getWeatherData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiKey = "abe29e70ba9b464104a51b15dcfef1be"
                val city = "Bishkek"
                val response = repository.getCurrentWeather(city, apiKey)
                response.enqueue(object : Callback<WeatherResponse> {
                    override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) {
                                _result.postValue(Result.success(body))
                            } else {
                                _result.postValue(Result.failure(Throwable("Response body is null")))
                            }
                        } else {
                            _result.postValue(Result.failure(Throwable("Failed to get weather data")))
                        }
                    }

                    override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                        _result.postValue(Result.failure(t))
                    }
                })
            } catch (e: Exception) {
                _result.postValue(Result.failure(e))
            }
        }
    }
}