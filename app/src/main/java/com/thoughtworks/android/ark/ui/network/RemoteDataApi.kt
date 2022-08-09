package com.thoughtworks.android.ark.ui.network

import com.thoughtworks.android.ark.ui.network.di.HttpClient
import com.thoughtworks.android.core.network.client.RetrofitClient
import com.thoughtworks.android.core.network.entity.RetrofitResponse
import com.thoughtworks.android.core.network.util.performFlowRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataApi @Inject constructor(@HttpClient retrofitClient: RetrofitClient) {
    private val apiService = retrofitClient.createService(
        DemoResponseApiService::class.java,
        "https://www.wanandroid.com"
    )

    suspend fun getData() : Flow<RetrofitResponse<FriendListResponse>> {
        return performFlowRequest { apiService.getAllData() }
    }
}