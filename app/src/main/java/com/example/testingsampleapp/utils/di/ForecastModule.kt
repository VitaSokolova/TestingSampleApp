package com.example.testingsampleapp.utils.di

import com.example.testingsampleapp.data.*
import com.example.testingsampleapp.domain.ForecastRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ForecastModule {
    companion object {
        @Provides
        @Singleton
        fun provideForecastApi(retrofit: Retrofit): ForecastApi = retrofit.create(ForecastApi::class.java)
    }

    @Binds
    @Singleton
    abstract fun provideForecastDataSource(forecastDataSourceImpl: ForecastDataSourceImpl): ForecastDataSource

    @Binds
    @Singleton
    abstract fun provideForecastRepository(forecastRepositoryImpl: ForecastRepositoryImpl): ForecastRepository

}