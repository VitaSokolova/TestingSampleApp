package com.example.testingsampleapp.utils.di

import com.example.testingsampleapp.utils.dispatchers.DefaultDispatcherProvider
import com.example.testingsampleapp.utils.dispatchers.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Singleton
    @Binds
    abstract fun bindDispatcherProvider(
        dispatcherProvider: DefaultDispatcherProvider
    ): DispatcherProvider
}