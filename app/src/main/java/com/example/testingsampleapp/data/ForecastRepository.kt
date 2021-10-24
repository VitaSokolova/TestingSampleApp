package com.example.testingsampleapp.data

import com.example.testingsampleapp.domain.models.DailyForecast

interface ForecastRepository {

    suspend fun getDailyForecast(cityId: String): DailyForecast
}