package com.thoughtworks.android.ark.ui.dashboard

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
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

interface DashboardDataSource {
    suspend fun getData(): String
}

class LocalDashboardDataSource : DashboardDataSource {
    override suspend fun getData(): String {
        return "Local Data"
    }
}

class RemoteDashboardDataSource : DashboardDataSource {
    override suspend fun getData(): String {
        return "Remote Data"
    }
}