package com.example.travelapp.presentation.view.weather

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.travelapp.databinding.FragmentWeatherPageBinding
import com.example.travelapp.presentation.viewModel.weather.GetWeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


class WeatherPageFragment : Fragment() {
    private var _binding: FragmentWeatherPageBinding? = null
    private val binding: FragmentWeatherPageBinding get() = _binding!!
    private val viewModel: GetWeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        viewModel.getWeatherData()
        viewModel.result.observe(viewLifecycleOwner, Observer {
            result -> result.onSuccess { weather ->
                val currentDate = LocalDate.now()
                val formattedDate = currentDate.format(DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale.ENGLISH))

                binding.weatherDate.text = "Today, $formattedDate"
                val tempCelsius = (weather.main.temp - 273.15).toInt()
                binding.weatherGradus.text = tempCelsius.toString()
                binding.weatherDescription.text = weather.weather[0].description
                binding.weatherWind.text = "${weather.wind.speed} km/h"
                binding.weatherHum.text = "${weather.main.humidity} %"
            }
        })
    }
}