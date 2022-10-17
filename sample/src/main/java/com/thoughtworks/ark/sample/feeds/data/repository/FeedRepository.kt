package com.thoughtworks.ark.sample.feeds.data.repository

import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.sample.feeds.data.repository.entity.FeedListEntity
import com.thoughtworks.ark.sample.feeds.data.repository.source.FeedApiDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FeedRepository @Inject constructor(
    private val remote: FeedApiDataSource,
) {
    suspend fun getFeedList(): Flow<Result<FeedListEntity>> = remote.getFeedList()
}