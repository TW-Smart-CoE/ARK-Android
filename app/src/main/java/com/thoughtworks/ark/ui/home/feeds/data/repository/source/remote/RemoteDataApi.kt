package com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote

import com.thoughtworks.ark.core.network.api.ApiService
import com.thoughtworks.ark.core.network.client.ApiEndPoints
import com.thoughtworks.ark.core.network.entity.ApiException
import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FriendListEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject

class RemoteDataApi @Inject constructor(apiEndPoints: ApiEndPoints) :
    ApiService() {
    private val apiService = apiEndPoints.createService(
        FriendApiService::class.java,
        "https://www.wanandroid.com"
    )

    suspend fun getFriendList(): Flow<Result<FriendListEntity>> {
        return performRequest { apiService.getFriendList() }
    }

    override fun mapNetworkThrowable(throwable: Throwable): ApiException {
        return super.mapNetworkThrowable(throwable)
    }

    override fun parseResponseError(
        code: Int,
        message: String,
        errorBody: ResponseBody?
    ): ApiException {
        return super.parseResponseError(code, message, errorBody)
    }
}