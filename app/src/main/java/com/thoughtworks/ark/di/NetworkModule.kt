package com.thoughtworks.ark.di

import android.content.Context
import com.thoughtworks.ark.core.network.client.ApiEndPoints
import com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote.FriendApiDataSource
import com.thoughtworks.ark.ui.home.feeds.data.repository.source.remote.RetrofitFriendApiDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun providerFriendApiDataSource(apiEndPoints: ApiEndPoints): FriendApiDataSource =
        RetrofitFriendApiDataSource(apiEndPoints)

    @Provides
    fun provideApiService(@ApplicationContext context: Context): ApiEndPoints =
        ApiEndPoints(context)
}