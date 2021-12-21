package com.example.testingsampleapp.presentation.overview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.testingsampleapp.R
import com.example.testingsampleapp.databinding.ActivityWeatherOverviewBinding
import com.example.testingsampleapp.presentation.overview.models.ForecastState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherOverviewActivity : AppCompatActivity() {

    private val viewModel: WeatherOverviewViewModel by viewModels()
    private lateinit var binding: ActivityWeatherOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
        observeViewModel()
        viewModel.loadForecast()
    }

    private fun initListeners() {
        binding.retryBtn.setOnClickListener { viewModel.onRetryButtonClick() }
    }

    private fun observeViewModel() {
        viewModel.forecastState.observe(this, ::renderForecastState)
    }

    private fun renderForecastState(forecastState: ForecastState) = with(binding) {
        if (forecastState is ForecastState.Data) {
            dayTemperature.text = resources.getString(
                R.string.day_temperature,
                forecastState.forecast.dayTemperature
            )
            nightTemperature.text = resources.getString(
                R.string.night_temperature,
                forecastState.forecast.nightTemperature
            )
            currentTemperature.text = resources.getString(
                R.string.current_temperature,
                forecastState.forecast.currentTemperature
            )
        }

        loadingIndicator.isVisible = forecastState is ForecastState.Loading
        errorContainer.isVisible = forecastState is ForecastState.Error
        dataContainer.isVisible = forecastState is ForecastState.Data
    }
}