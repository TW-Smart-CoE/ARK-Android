package com.thoughtworks.ark.ui.home.feeds.data.repository

import com.google.common.truth.Truth.assertThat
import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FeedListEntity
import com.thoughtworks.ark.ui.home.feeds.data.repository.source.FeedApiDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FeedRepositoryTest {

    private val feedList = FeedListEntity(0, null)

    private val feedApiDataSource = mockk<FeedApiDataSource>()
    private val feedRepository = FeedRepository(feedApiDataSource)

    @Before
    fun setUp() {
        coEvery { feedApiDataSource.getFeedList() } returns flowOf(Result.Success(feedList))
    }

    @Test
    fun shouldCallDataSourceWhenFetchFeedListFromRepository() = runTest {
        // when
        val result = feedRepository.getFeedList().first()
        // then
        coVerify(exactly = 1) { feedApiDataSource.getFeedList() }
        assertThat((result as Result.Success).data).isEqualTo(feedList)
    }
}
