package com.example.testingsampleapp.data

import com.example.testingsampleapp.domain.models.DailyForecast
import retrofit2.http.Path

interface ForecastDataSource {

    suspend fun getDailyForecast(cityId: String): DailyForecast
}