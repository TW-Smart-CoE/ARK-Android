package com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote

import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FeedListEntity
import kotlinx.coroutines.flow.Flow

interface FeedApiDataSource {
    suspend fun getFeedList(): Flow<Result<FeedListEntity>>
}