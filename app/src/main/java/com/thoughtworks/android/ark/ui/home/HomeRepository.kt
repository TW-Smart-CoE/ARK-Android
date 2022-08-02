package com.thoughtworks.android.ark.ui.home

import javax.inject.Inject

class HomeRepository @Inject constructor(private val dataSource: HomeDataSource) {
    suspend fun loadData() = dataSource.getData()
}