package com.thoughtworks.android.ark.ui.home.feeds.data.repository.source.remote

import com.thoughtworks.android.ark.di.HttpClient
import com.thoughtworks.android.ark.ui.home.feeds.data.repository.entity.FriendListEntity
import com.thoughtworks.android.core.network.client.RetrofitClient
import com.thoughtworks.android.core.network.entity.RetrofitResponse
import com.thoughtworks.android.core.network.util.performFlowRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataApi @Inject constructor(@HttpClient retrofitClient: RetrofitClient) {
    private val apiService = retrofitClient.createService(
        FriendApiService::class.java,
        "https://www.wanandroid.com"
    )

    suspend fun getFriendList(): Flow<RetrofitResponse<FriendListEntity>> {
        return performFlowRequest { apiService.getFriendList() }
    }
}