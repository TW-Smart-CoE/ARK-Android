package com.thoughtworks.ark.di

import android.content.Context
import com.thoughtworks.ark.core.network.client.ApiEndPoints
import com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote.FeedApiDataSource
import com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote.RetrofitFeedApiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providerFeedApiDataSource(apiEndPoints: ApiEndPoints): FeedApiDataSource =
        RetrofitFeedApiDataSource(apiEndPoints)

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