package com.example.testingsampleapp.data

import com.example.testingsampleapp.data.models.DailyForecastDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ForecastApi {

    /**
     * Date in the format yyyy/mm/dd
     */
    @GET("api/location/{cityId}/{date}/")
    suspend fun getDailyForecast(
        @Path("cityId") cityId: String,
        @Path("date") date: String
    ): List<DailyForecastDto>
}