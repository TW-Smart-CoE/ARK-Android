package com.thoughtworks.android.ark.ui.network

import com.thoughtworks.android.ark.ui.network.di.HttpClient
import com.thoughtworks.network.client.RetrofitClient
import com.thoughtworks.network.entity.RetrofitResponse
import com.thoughtworks.network.util.performRequest
import javax.inject.Inject

class RemoteDataApi @Inject constructor(@HttpClient retrofitClient: RetrofitClient) {
    private val apiService = retrofitClient.createService(
        DemoApiService::class.java,
        "https://www.wanandroid.com"
    )

    suspend fun getData() : RetrofitResponse {
        val result = apiService.getAllData()
        return performRequest(result)
    }
}