package com.example.testingsampleapp.utils

import com.example.testingsampleapp.utils.dispatchers.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@ExperimentalCoroutinesApi
class TestDispatcherProvider : DispatcherProvider {
    override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()
}