package com.example.testingsampleapp.data.models

import com.example.testingsampleapp.domain.models.DailyForecast
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter.ISO_INSTANT

fun DailyForecastDto.toModel(): DailyForecast {
    return DailyForecast(
        created = ZonedDateTime.parse(
            created,
            ISO_INSTANT.withZone(ZoneId.systemDefault())
        ).toLocalDateTime(),
        currentTemperature = currentTemperature,
        dayTemperature = dayTemperature,
        nightTemperature = nightTemperature
    )
}