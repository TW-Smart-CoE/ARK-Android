package com.thoughtworks.android.ark.ui.network

import com.thoughtworks.network.callback.BaseCallModel
import retrofit2.Call
import retrofit2.http.GET

interface DemoApiService {
    @GET("/friend/json")
    fun getAllData(): Call<BaseCallModel<FriendListResponse>>
}