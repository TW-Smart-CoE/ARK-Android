package com.thoughtworks.android.ark.ui.network.di

import android.content.Context
import com.thoughtworks.android.core.network.client.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class HttpClient


@InstallIn(SingletonComponent::class)
@Module
object RetrofitClientModule {

    @HttpClient
    @Provides
    fun provideHttpClient(@ApplicationContext context: Context): RetrofitClient = RetrofitClient(context)
}