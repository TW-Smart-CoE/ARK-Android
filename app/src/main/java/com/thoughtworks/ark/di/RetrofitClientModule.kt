package com.thoughtworks.ark.di

import android.content.Context
import com.thoughtworks.ark.core.network.client.ApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object RetrofitClientModule {

    @Provides
    fun provideApiService(@ApplicationContext context: Context): ApiEndPoints =
        ApiEndPoints(context)
}