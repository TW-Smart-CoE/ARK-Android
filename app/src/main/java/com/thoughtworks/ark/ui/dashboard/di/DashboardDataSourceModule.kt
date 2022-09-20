package com.thoughtworks.ark.ui.dashboard.di

import com.thoughtworks.ark.ui.dashboard.repository.source.DashboardDataSource
import com.thoughtworks.ark.ui.dashboard.repository.source.LocalDashboardDataSource
import com.thoughtworks.ark.ui.dashboard.repository.source.RemoteDashboardDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource

@InstallIn(SingletonComponent::class)
@Module
object DashboardDataSourceModule {
    @LocalDataSource
    @Provides
    fun provideLocalDataSource(): DashboardDataSource = LocalDashboardDataSource()

    @RemoteDataSource
    @Provides
    fun provideRemoteDataSource(): DashboardDataSource = RemoteDashboardDataSource()
}