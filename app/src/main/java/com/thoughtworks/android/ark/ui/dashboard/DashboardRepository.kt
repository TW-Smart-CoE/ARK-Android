package com.thoughtworks.android.ark.ui.dashboard

import com.thoughtworks.android.ark.ui.dashboard.di.LocalDataSource
import com.thoughtworks.android.ark.ui.dashboard.di.RemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    @LocalDataSource private val localDataSource: DashboardDataSource,
    @RemoteDataSource private val remoteDataSource: DashboardDataSource
) {
    suspend fun loadData() = flow {
        emit(localDataSource.getData())

        delay(2000)

        emit(remoteDataSource.getData())
    }
}