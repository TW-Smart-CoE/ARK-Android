package com.thoughtworks.demo

import com.thoughtworks.network.entity.RetrofitResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(private val remoteDataApi: RemoteDataApi) {
    suspend fun getData() : Flow<RetrofitResponse> {
        return flow {
            emit(RetrofitResponse.Loading)
            emit(remoteDataApi.getData())
        }
    }
}