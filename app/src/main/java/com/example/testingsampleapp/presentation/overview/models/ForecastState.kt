package com.example.testingsampleapp.presentation.overview.models

import com.example.testingsampleapp.domain.models.DailyForecast

sealed class ForecastState {
    object Loading : ForecastState()
    data class Data(val forecast: DailyForecast) : ForecastState()
    object Error : ForecastState()
}