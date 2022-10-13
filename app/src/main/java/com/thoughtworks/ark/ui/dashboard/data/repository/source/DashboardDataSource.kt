package com.thoughtworks.ark.ui.dashboard.data.repository.source

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