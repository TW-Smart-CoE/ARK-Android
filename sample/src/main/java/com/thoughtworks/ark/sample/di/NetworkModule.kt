package com.thoughtworks.ark.sample.di

import android.content.Context
import com.thoughtworks.ark.core.network.client.ApiEndPoints
import com.thoughtworks.ark.sample.feeds.data.repository.source.FeedApiDataSource
import com.thoughtworks.ark.sample.feeds.data.repository.source.RemoteFeedApiDataSource
import com.thoughtworks.ark.sample.feeds.data.repository.source.RetrofitFeedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providerFeedApi(apiEndPoints: ApiEndPoints): RetrofitFeedApi =
        apiEndPoints.createService(RetrofitFeedApi::class.java, RetrofitFeedApi.baseUrl)

    @Provides
    @Singleton
    fun providerFeedApiDataSource(
        feedApi: RetrofitFeedApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): FeedApiDataSource = RemoteFeedApiDataSource(feedApi, ioDispatcher)

    @Provides
    @Singleton
    fun provideApiService(@ApplicationContext context: Context, json: Json): ApiEndPoints =
        ApiEndPoints(context, json)

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}
