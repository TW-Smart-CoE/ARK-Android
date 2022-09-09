package com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote

import com.thoughtworks.ark.core.network.api.ApiService
import com.thoughtworks.ark.core.network.client.ApiEndPoints
import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FeedListEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

private interface RetrofitFeedApi {
    @GET("/friend/json")
    suspend fun getFeedList(): Response<FeedListEntity>
}

class RetrofitFeedApiDataSource @Inject constructor(apiEndPoints: ApiEndPoints) :
    ApiService(), FeedApiDataSource {
    private val apiService = apiEndPoints.createService(
        RetrofitFeedApi::class.java,
        "https://www.wanandroid.com"
    )

    override suspend fun getFeedList(): Flow<Result<FeedListEntity>> {
        return performRequest { apiService.getFeedList() }
    }
}