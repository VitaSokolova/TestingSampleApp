package com.example.testingsampleapp.domain

import com.example.testingsampleapp.domain.models.DailyForecast

interface ForecastRepository {

    suspend fun getDailyForecast(cityId: String): DailyForecast
}