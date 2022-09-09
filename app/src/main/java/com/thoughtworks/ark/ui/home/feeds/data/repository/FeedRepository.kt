package com.thoughtworks.ark.ui.home.feeds.data.repository

import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FeedListEntity
import com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote.FeedApiDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val remote: FeedApiDataSource,
) {
    suspend fun getFeedList(): Flow<Result<FeedListEntity>> =
        remote.getFeedList()
}