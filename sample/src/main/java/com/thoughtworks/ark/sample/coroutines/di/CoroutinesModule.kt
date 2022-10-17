package com.thoughtworks.ark.sample.coroutines.di

import com.thoughtworks.ark.sample.coroutines.data.repository.source.CoroutinesDataSource
import com.thoughtworks.ark.sample.coroutines.data.repository.source.LocalCoroutinesDataSource
import com.thoughtworks.ark.sample.coroutines.data.repository.source.RemoteCoroutinesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource

@InstallIn(SingletonComponent::class)
@Module
class CoroutinesModule {
    @LocalDataSource
    @Singleton
    @Provides
    fun provideLocalDataSource(): CoroutinesDataSource = LocalCoroutinesDataSource()

    @RemoteDataSource
    @Singleton
    @Provides
    fun provideRemoteDataSource(): CoroutinesDataSource = RemoteCoroutinesDataSource()
}