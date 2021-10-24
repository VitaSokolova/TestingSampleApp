package com.example.testingsampleapp.data.model

import com.google.gson.annotations.SerializedName

data class DailyForecastResponse(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<DailyForecastDto>
)

data class DailyForecastDto(
    @SerializedName("the_temp")
    val currentTemperature: Double, // on the Celsius scale
    @SerializedName("max_temp")
    val dayTemperature: Double, // on the Celsius scale
    @SerializedName("min_temp")
    val nightTemperature: Double, // on the Celsius scale,
    @SerializedName("created")
    val created: String
)