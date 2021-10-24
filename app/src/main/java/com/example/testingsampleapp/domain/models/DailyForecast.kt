package com.example.testingsampleapp.domain.models

import java.time.LocalDateTime

data class DailyForecast(
    val created: LocalDateTime,
    val currentTemperature: Double,
    val dayTemperature: Double,
    val nightTemperature: Double,
)