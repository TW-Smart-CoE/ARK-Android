package com.thoughtworks.ark.ui.home.feeds.data.repository

import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FriendListEntity
import com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote.FriendApiDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val remote: FriendApiDataSource,
) {
    suspend fun getFriendList(): Flow<Result<FriendListEntity>> =
        remote.getFriendList()
}