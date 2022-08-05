package com.thoughtworks.android.ark.ui.network

import com.thoughtworks.network.entity.RetrofitResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val remoteDataApi: RemoteDataApi) {
    suspend fun getData() : Flow<RetrofitResponse> {
        return flow {
            emit(RetrofitResponse.Loading)
            emit(remoteDataApi.getData())
        }
    }
}