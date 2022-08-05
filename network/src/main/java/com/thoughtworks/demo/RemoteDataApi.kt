package com.thoughtworks.demo

import com.thoughtworks.network.client.RetrofitClient
import com.thoughtworks.network.entity.NetworkNotConnectException
import com.thoughtworks.network.entity.RetrofitResponse
import com.thoughtworks.network.util.performRequest

class RemoteDataApi(retrofitClient: RetrofitClient) {
    private val apiService = retrofitClient.createService(DemoApiService::class.java,"www.baidu.com")
    suspend fun getData() : RetrofitResponse {
        val result = try {
            apiService.getAllData()
        } catch (e:NetworkNotConnectException) {
            return RetrofitResponse.Error(Exception("network is not connect"))
        }
        return performRequest(result)
    }
}