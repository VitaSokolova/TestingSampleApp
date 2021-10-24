package com.example.testingsampleapp.domain

import com.example.testingsampleapp.data.ForecastRepository
import com.example.testingsampleapp.domain.models.DailyForecast
import javax.inject.Inject

class GetDailyForecastUseCase @Inject constructor(
    private val forecastRepository: ForecastRepository
) {

    suspend operator fun invoke(cityId: String): DailyForecast {
        return forecastRepository.getDailyForecast(cityId)
    }
}