package com.example.testingsampleapp

import androidx.lifecycle.Observer
import com.example.testingsampleapp.domain.GetDailyForecastUseCase
import com.example.testingsampleapp.domain.models.DailyForecast
import com.example.testingsampleapp.presentation.overview.WeatherOverviewViewModel
import com.example.testingsampleapp.presentation.overview.models.ForecastState
import com.example.testingsampleapp.utils.dispatchers.TestDispatcherProvider
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test
import java.time.LocalDateTime
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.testingsampleapp.utils.CoroutinesTestExtension
import io.mockk.coVerify
import org.junit.Rule
import org.junit.jupiter.api.extension.RegisterExtension
import java.lang.Exception

class WeatherOverviewViewModelTest {

    @JvmField
    @RegisterExtension
    val coroutinesTestExtension = CoroutinesTestExtension()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val mockForecast = DailyForecast(
        created = LocalDateTime.now(),
        currentTemperature = 18.2,
        dayTemperature = 20.1,
        nightTemperature = 22.0
    )
    private val mockGetDailyForecastUseCase = mockk<GetDailyForecastUseCase>()

    @Test
    fun `when getting forecast ends well should show load state and data`() {
        //given
        coEvery { mockGetDailyForecastUseCase.invoke(any()) } returns mockForecast
        val viewModel = WeatherOverviewViewModel(TestDispatcherProvider(), mockGetDailyForecastUseCase)
        val observer = mockk<Observer<ForecastState>>(relaxed = true)
        viewModel.forecastState.observeForever(observer)

        //when
        viewModel.loadForecast()

        //then
        Truth.assertThat(observer.onChanged(ForecastState.Loading))
        Truth.assertThat(observer.onChanged(ForecastState.Data(mockForecast)))
    }

    @Test
    fun `when getting forecast ends with error should show load state and error`() {
        coEvery { mockGetDailyForecastUseCase.invoke(any()) } throws Exception()
        val viewModel = WeatherOverviewViewModel(TestDispatcherProvider(), mockGetDailyForecastUseCase)
        val observer = mockk<Observer<ForecastState>>(relaxed = true)
        viewModel.forecastState.observeForever(observer)

        viewModel.loadForecast()

        Truth.assertThat(observer.onChanged(ForecastState.Loading))
        Truth.assertThat(observer.onChanged(ForecastState.Error))
    }

    @Test
    fun `when reload button is clicked should send request`() {
        val viewModel = WeatherOverviewViewModel(TestDispatcherProvider(), mockGetDailyForecastUseCase)

        viewModel.onRetryButtonClick()

        coVerify { mockGetDailyForecastUseCase(any()) }
    }
}