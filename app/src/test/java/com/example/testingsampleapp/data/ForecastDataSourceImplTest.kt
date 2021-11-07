package com.example.testingsampleapp.data

import com.example.testingsampleapp.data.model.DailyForecastDto
import com.example.testingsampleapp.data.model.toModel
import com.example.testingsampleapp.presentation.overview.LONDON_ID
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.time.LocalDate

@ExperimentalCoroutinesApi
class ForecastDataSourceImplTest {

    private val yesterdayForecast = DailyForecastDto(
        currentTemperature = 10.0,
        dayTemperature = 12.0,
        nightTemperature = 8.2,
        created = "2021-10-23T03:59:03.439997Z"
    )
    private val todayForecast = DailyForecastDto(
        currentTemperature = 11.0,
        dayTemperature = 12.8,
        nightTemperature = 7.2,
        created = "2021-10-24T01:59:03.439997Z"
    )
    private val forecastsList = listOf(
        yesterdayForecast,
        todayForecast
    )
    private val mockForecastApi = mockk<ForecastApi>()
    private val dataSource = ForecastDataSourceImpl(mockForecastApi)

    @Test
    fun `check today date formatting for request`() {
        coEvery { mockForecastApi.getDailyForecast(any(), any()) } returns forecastsList
        val date = LocalDate.of(2011, 8, 10)
        val expectedDateParameter = "2011/8/10"

        val actualDateFormat = dataSource.formatDateToApiFormat(date)

        Truth.assertThat(actualDateFormat).isEqualTo(expectedDateParameter)
    }

    @Test
    fun `check selecting the most recent forecast from response`() = runBlockingTest {
        coEvery { mockForecastApi.getDailyForecast(any(), any()) } returns forecastsList

        val dailyForecast = dataSource.getDailyForecast(LONDON_ID)

        Truth.assertThat(dailyForecast).isEqualTo(todayForecast.toModel())
    }
}