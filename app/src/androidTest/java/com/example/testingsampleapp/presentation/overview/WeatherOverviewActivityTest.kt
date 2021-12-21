package com.example.testingsampleapp.presentation.overview

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.testingsampleapp.R
import com.example.testingsampleapp.data.ForecastRepository
import com.example.testingsampleapp.domain.models.DailyForecast
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.every
import kotlinx.coroutines.delay
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class WeatherOverviewActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var forecastRepository: ForecastRepository

    private val dailyForecast = DailyForecast(
        currentTemperature = 40.0,
        dayTemperature = 42.0,
        nightTemperature = 48.2,
        created = LocalDateTime.now()
    )

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun whenDataIsLoadedShouldShowRightCurrentTemperatureText() {
        coEvery { forecastRepository.getDailyForecast(any()) } returns dailyForecast
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedText = context.resources.getString(
            R.string.current_temperature,
            dailyForecast.currentTemperature
        )

        ActivityScenario.launch(WeatherOverviewActivity::class.java).use {
            onView(withId(R.id.current_temperature))
                .check(matches(withText(expectedText)))
        }
    }

    @Test
    fun whenDataIsLoadedShouldShowRightNightTemperatureText() {
        coEvery { forecastRepository.getDailyForecast(any()) } returns dailyForecast
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedText = context.resources.getString(
            R.string.night_temperature,
            dailyForecast.nightTemperature
        )

        ActivityScenario.launch(WeatherOverviewActivity::class.java).use {
            onView(withId(R.id.night_temperature))
                .check(matches(withText(expectedText)))
        }
    }

    @Test
    fun whenDataIsLoadedShouldShowRightDayTemperatureText() {
        coEvery { forecastRepository.getDailyForecast(any()) } returns dailyForecast
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val expectedText = context.resources.getString(
            R.string.day_temperature,
            dailyForecast.dayTemperature
        )

        ActivityScenario.launch(WeatherOverviewActivity::class.java).use {
            onView(withId(R.id.day_temperature))
                .check(matches(withText(expectedText)))
        }
    }

    @Test
    fun whenDataIsLoadingShouldShowLoader() {
        coEvery { forecastRepository.getDailyForecast(any()) } coAnswers {
            delay(1000L)
            dailyForecast
        }

        ActivityScenario.launch(WeatherOverviewActivity::class.java).use {
            onView(withId(R.id.loading_indicator))
                .check(matches(isDisplayed()))
            onView(withId(R.id.error_container))
                .check(matches(not(isDisplayed())))
            onView(withId(R.id.data_container))
                .check(matches(not(isDisplayed())))
        }
    }

    @Test
    fun whenDataIsLoadedShouldShowTemperatureTexts(){
        //todo:
    }

    @Test
    fun whenLoadingFailsShouldShowErrorState() {
        //todo:
    }
}