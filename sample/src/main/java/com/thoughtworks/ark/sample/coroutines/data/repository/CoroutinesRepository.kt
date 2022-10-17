package com.thoughtworks.ark.sample.coroutines.data.repository

import com.thoughtworks.ark.sample.coroutines.data.repository.source.CoroutinesDataSource
import com.thoughtworks.ark.sample.coroutines.di.LocalDataSource
import com.thoughtworks.ark.sample.coroutines.di.RemoteDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CoroutinesRepository @Inject constructor(
    @LocalDataSource private val localDataSource: CoroutinesDataSource,
    @RemoteDataSource private val remoteDataSource: CoroutinesDataSource,
) {
    suspend fun loadData() = flow {
        emit(localDataSource.getData())

        delay(DELAY_TIMES)

        emit(remoteDataSource.getData())
    }

    companion object {
        const val DELAY_TIMES: Long = 2000
    }
}