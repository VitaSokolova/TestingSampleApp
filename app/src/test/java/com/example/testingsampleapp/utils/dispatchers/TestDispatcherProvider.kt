package com.example.testingsampleapp.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Inject

class TestDispatcherProvider @Inject constructor() : DispatcherProvider {
    override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()
}