package com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote

import com.thoughtworks.ark.core.network.api.ApiService
import com.thoughtworks.ark.core.network.client.ApiEndPoints
import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FriendListEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

private interface RetrofitFriendApi {
    @GET("/friend/json")
    suspend fun getFriendList(): Response<FriendListEntity>
}

class RetrofitFriendApiDataSource @Inject constructor(apiEndPoints: ApiEndPoints) :
    ApiService(), FriendApiDataSource {
    private val apiService = apiEndPoints.createService(
        RetrofitFriendApi::class.java,
        "https://www.wanandroid.com"
    )

    override suspend fun getFriendList(): Flow<Result<FriendListEntity>> {
        return performRequest { apiService.getFriendList() }
    }
}