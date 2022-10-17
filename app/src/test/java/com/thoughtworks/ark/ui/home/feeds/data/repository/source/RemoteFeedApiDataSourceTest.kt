package com.thoughtworks.ark.ui.home.feeds.data.repository.source

import com.google.common.truth.Truth.assertThat
import com.thoughtworks.ark.MainCoroutineRule
import com.thoughtworks.ark.core.network.entity.ApiException
import com.thoughtworks.ark.core.network.entity.Result
import com.thoughtworks.ark.ui.home.feeds.data.repository.entity.FeedListEntity
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class RemoteFeedApiDataSourceTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val feedApi: RetrofitFeedApi = mockk()
    private lateinit var retrofitFeedApiDataSource: RemoteFeedApiDataSource

    @Before
    fun setUp() {
        retrofitFeedApiDataSource =
            RemoteFeedApiDataSource(feedApi, mainCoroutineRule.testDispatcher)
    }

    @Test
    fun shouldReturnSuccessResultWhenApiMethodExecuteCorrectly() = runTest {
        // given
        val feedList = FeedListEntity()
        coEvery { feedApi.getFeedList() } returns Response.success(feedList)
        // when
        val results = retrofitFeedApiDataSource.getFeedList().toList()
        // then
        assertThat(results.first()).isEqualTo(Result.Loading)
        assertThat((results.last() as Result.Success).data).isEqualTo(feedList)
    }

    @Test
    fun shouldReturnErrorResultWhenApiMethodThrowAnException() = runTest {
        // given
        val exception = IllegalStateException("error")
        coEvery { feedApi.getFeedList() } throws exception
        // when
        val results = retrofitFeedApiDataSource.getFeedList().toList()
        // then
        assertThat(results.first()).isEqualTo(Result.Loading)
        assertThat((results.last() as Result.Error).exception)
            .isInstanceOf(ApiException::class.java)
    }
}
