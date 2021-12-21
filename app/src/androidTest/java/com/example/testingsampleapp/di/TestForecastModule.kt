package com.example.testingsampleapp.di

import com.example.testingsampleapp.data.*
import com.example.testingsampleapp.utils.di.DispatchersModule
import com.example.testingsampleapp.utils.di.ForecastModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ForecastModule::class]
)
class TestForecastModule {

    private val mockApi: ForecastApi by lazy { mockk(relaxed = true) }
    private val mockForecastDataSource: ForecastDataSource by lazy { mockk(relaxed = true) }
    private val mockForecastRepository: ForecastRepository by lazy { mockk(relaxed = true) }

    @Provides
    @Singleton
    fun provideForecastApi(): ForecastApi = mockApi

    @Provides
    @Singleton
    fun provideForecastDataSource(): ForecastDataSource = mockForecastDataSource

    @Provides
    @Singleton
    fun provideForecastRepository(): ForecastRepository = mockForecastRepository
}