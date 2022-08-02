package com.thoughtworks.android.ark.ui.home

import com.thoughtworks.android.ark.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getData() = withContext(ioDispatcher) {
        delay(1000)
        "Test data"
    }
}