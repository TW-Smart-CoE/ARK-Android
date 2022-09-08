package com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote

import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FriendListEntity
import kotlinx.coroutines.flow.Flow

interface FriendApiDataSource {
    suspend fun getFriendList(): Flow<Result<FriendListEntity>>
}