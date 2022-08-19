package com.thoughtworks.ark.ui.home

import com.thoughtworks.ark.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeDataSource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getData() = withContext(ioDispatcher) {
        delay(DELAY_TIMES)
        "Test data"
    }

    companion object {
        const val DELAY_TIMES: Long = 1000
    }
}