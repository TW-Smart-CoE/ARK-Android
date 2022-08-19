package com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote

import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FriendListEntity
import retrofit2.Response
import retrofit2.http.GET

interface FriendApiService {
    @GET("/friend/json")
    suspend fun getFriendList(): Response<FriendListEntity>
}