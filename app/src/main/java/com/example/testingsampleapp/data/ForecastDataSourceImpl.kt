package com.example.testingsampleapp.data

import android.util.Log
import com.example.testingsampleapp.data.model.toModel
import com.example.testingsampleapp.domain.models.DailyForecast
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForecastDataSourceImpl @Inject constructor(private val forecastApi: ForecastApi) : ForecastDataSource {

    override suspend fun getDailyForecast(cityId: String): DailyForecast {
        val today = LocalDate.now()
        val datePath = with(today) { "$year/$monthValue/$dayOfMonth" }
        val forecasts = forecastApi.getDailyForecast(cityId, datePath).map { it.toModel() }
        return forecasts.sortedByDescending { it.created }.first()
    }
}