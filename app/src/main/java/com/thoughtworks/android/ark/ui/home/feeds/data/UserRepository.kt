package com.thoughtworks.android.ark.ui.home.feeds.data

import com.thoughtworks.android.ark.ui.home.feeds.data.repository.entity.FriendListEntity
import com.thoughtworks.android.ark.ui.home.feeds.data.repository.source.remote.RemoteDataApi
import com.thoughtworks.android.core.network.entity.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val remoteDataApi: RemoteDataApi) {
    suspend fun getFriendList(): Flow<Result<FriendListEntity>> =
        remoteDataApi.getFriendList()
}