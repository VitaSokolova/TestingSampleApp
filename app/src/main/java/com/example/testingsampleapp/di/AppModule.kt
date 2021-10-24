package com.example.testingsampleapp.di

import com.example.testingsampleapp.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://www.metaweather.com/")
        .build()

    @Provides
    @Singleton
    fun provideForecastApi(retrofit: Retrofit): ForecastApi = retrofit.create(ForecastApi::class.java)

    @Provides
    @Singleton
    fun provideForecastDataSource(forecastApi: ForecastApi): ForecastDataSource = ForecastDataSourceImpl(forecastApi)

    @Provides
    @Singleton
    fun provideForecastRepository(forecastDataSource: ForecastDataSource): ForecastRepository = ForecastRepositoryImpl(forecastDataSource)
}