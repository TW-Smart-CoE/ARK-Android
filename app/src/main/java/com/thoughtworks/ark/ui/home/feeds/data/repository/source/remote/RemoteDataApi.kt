package com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote

import com.thoughtworks.ark.core.network.api.RootApiService
import com.thoughtworks.ark.core.network.client.RetrofitClient
import com.thoughtworks.ark.core.network.entity.ApiException
import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.di.HttpClient
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FriendListEntity
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import javax.inject.Inject

class RemoteDataApi @Inject constructor(@HttpClient retrofitClient: RetrofitClient) :
    RootApiService() {
    private val apiService = retrofitClient.createService(
        FriendApiService::class.java,
        "https://www.wanandroid.com"
    )

    suspend fun getFriendList(): Flow<Result<FriendListEntity>> {
        return performFlowRequest { apiService.getFriendList() }
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