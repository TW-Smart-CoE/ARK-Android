package com.thoughtworks.android.ark.ui.network

import retrofit2.Response
import retrofit2.http.GET

interface DemoResponseApiService {
    @GET("/friend/json")
    suspend fun getAllData(): Response<FriendListResponse>
}