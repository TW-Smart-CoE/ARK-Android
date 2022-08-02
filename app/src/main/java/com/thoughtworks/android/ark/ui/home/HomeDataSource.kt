package com.thoughtworks.android.ark.ui.home

import kotlinx.coroutines.delay
import javax.inject.Inject

class HomeDataSource @Inject constructor() {
    suspend fun getData(): String {
        delay(1000)
        return "Test data"
    }
}