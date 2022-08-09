package com.thoughtworks.android.ark.ui.network

import com.thoughtworks.android.core.network.entity.RetrofitResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val remoteDataApi: RemoteDataApi) {
    suspend fun getData() : Flow<RetrofitResponse<FriendListResponse>> = remoteDataApi.getData()
}