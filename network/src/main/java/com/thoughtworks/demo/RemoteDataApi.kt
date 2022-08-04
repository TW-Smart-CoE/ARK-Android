package com.thoughtworks.demo

import com.thoughtworks.network.callback.RetrofitCallback
import com.thoughtworks.network.client.RetrofitClient
import com.thoughtworks.network.entity.RetrofitResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class RemoteDataApi(retrofitClient: RetrofitClient) {
    private var apiService:DemoApiService
    init {
        apiService = retrofitClient.createService(DemoApiService::class.java,"http://www.baidu.com")
    }

    suspend fun getData() : RetrofitResponse {
        val call = apiService.getAllData()
        return suspendCancellableCoroutine {
            call.enqueue(
                object : RetrofitCallback<DataEntity>() {
                    override fun onSuccess(data: DataEntity) {
                        it.resume(RetrofitResponse.Success(data))
                    }

                    override fun onFailed(msg: String) {
                        it.resume(RetrofitResponse.Error(Exception(msg)))
                    }
                }
            )
        }
    }
}