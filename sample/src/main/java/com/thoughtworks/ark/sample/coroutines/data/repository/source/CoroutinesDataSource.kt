package com.thoughtworks.ark.sample.coroutines.data.repository.source

interface CoroutinesDataSource {
    suspend fun getData(): String
}

class LocalCoroutinesDataSource : CoroutinesDataSource {
    override suspend fun getData(): String {
        return "Local Data"
    }
}

class RemoteCoroutinesDataSource : CoroutinesDataSource {
    override suspend fun getData(): String {
        return "Remote Data"
    }
}