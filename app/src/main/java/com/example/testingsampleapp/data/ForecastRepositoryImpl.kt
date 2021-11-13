package com.example.testingsampleapp.data

import com.example.testingsampleapp.domain.ForecastRepository
import com.example.testingsampleapp.domain.models.DailyForecast
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastRepositoryImpl @Inject constructor(
    private val forecastDataSource: ForecastDataSource
) : ForecastRepository {

    override suspend fun getDailyForecast(cityId: String): DailyForecast {
        return forecastDataSource.getDailyForecast(cityId)
    }
}