package com.thoughtworks.android.ark.ui.dashboard

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