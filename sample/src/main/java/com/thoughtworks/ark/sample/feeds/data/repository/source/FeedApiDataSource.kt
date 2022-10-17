package com.thoughtworks.ark.sample.feeds.data.repository.source

import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.sample.feeds.data.repository.entity.FeedListEntity
import kotlinx.coroutines.flow.Flow

interface FeedApiDataSource {
    suspend fun getFeedList(): Flow<Result<FeedListEntity>>
}