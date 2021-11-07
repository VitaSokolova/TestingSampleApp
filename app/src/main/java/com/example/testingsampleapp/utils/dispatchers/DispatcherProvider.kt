package com.example.testingsampleapp.utils.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatcherProvider {
    fun io(): CoroutineDispatcher
}

class DefaultDispatcherProvider @Inject constructor() : DispatcherProvider {
    override fun io(): CoroutineDispatcher = Dispatchers.IO
}