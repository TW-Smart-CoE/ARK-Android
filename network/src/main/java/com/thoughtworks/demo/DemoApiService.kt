package com.thoughtworks.demo

import com.thoughtworks.network.api.ApiService
import com.thoughtworks.network.callback.BaseCallModel
import retrofit2.Call
import retrofit2.http.GET

interface DemoApiService {
    @GET("test/getAllData")
    suspend fun getAllData(): Call<BaseCallModel<DataEntity>>
}