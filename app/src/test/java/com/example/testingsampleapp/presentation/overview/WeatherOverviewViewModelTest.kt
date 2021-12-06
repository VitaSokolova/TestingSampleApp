package com.example.testingsampleapp.presentation.overview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.testingsampleapp.domain.GetDailyForecastUseCase
import com.example.testingsampleapp.domain.models.DailyForecast
import com.example.testingsampleapp.presentation.overview.models.ForecastState
import com.example.testingsampleapp.utils.CoroutineRule
import com.example.testingsampleapp.utils.TestDispatcherProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verifySequence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.LocalDateTime

@ExperimentalCoroutinesApi
class WeatherOverviewViewModelTest {

    @ExperimentalCoroutinesApi
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutineRule = CoroutineRule()

    private val mockForecast = DailyForecast(
        created = LocalDateTime.now(),
        currentTemperature = 18.2,
        dayTemperature = 20.1,
        nightTemperature = 22.0
    )

    private val mockGetDailyForecastUseCase = mockk<GetDailyForecastUseCase>()
    private val viewModel = WeatherOverviewViewModel(
        getDailyForecastUseCase = mockGetDailyForecastUseCase,
        dispatcherProvider = TestDispatcherProvider()
    )
    private val observer = mockk<Observer<ForecastState>>(relaxed = true)

    @Test
    fun `when retrieving forecast ends well should show load state and data`() {
        //Arrange
        coEvery { mockGetDailyForecastUseCase.invoke(any()) } returns mockForecast
        viewModel.forecastState.observeForever(observer)

        //Act
        viewModel.loadForecast()

        //Assert
        verifySequence {
            observer.onChanged(ForecastState.Loading)
            observer.onChanged(ForecastState.Data(mockForecast))
        }
    }

    @Test
    fun `when retrieving forecast ends with error should show load state and error state`() {
        //Arrange
        coEvery { mockGetDailyForecastUseCase.invoke(any()) } throws Exception()
        viewModel.forecastState.observeForever(observer)

        //Act
        viewModel.loadForecast()

        //Assert
        verifySequence {
            observer.onChanged(ForecastState.Loading)
            observer.onChanged(ForecastState.Error)
        }
    }

    @Test
    fun `when reload button is clicked should send request again`() {
        //Act
        viewModel.onRetryButtonClick()
        //Assert
        coVerify { mockGetDailyForecastUseCase.invoke(any()) }
    }
}