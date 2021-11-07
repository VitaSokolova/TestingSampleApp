package com.example.testingsampleapp.utils.di

import com.example.testingsampleapp.utils.dispatchers.DispatcherProvider
import com.example.testingsampleapp.utils.dispatchers.TestDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatchersModule::class]
)
abstract class TestAppModule {

    @Singleton
    @Binds
    abstract fun bindDispatcherProvider(
        dispatcherProvider: TestDispatcherProvider
    ): DispatcherProvider
}