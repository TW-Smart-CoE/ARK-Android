package com.thoughtworks.ark.sample.di

import com.thoughtworks.ark.core.storage.FileManager
import com.thoughtworks.ark.core.storage.StorageInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun providerFileManager(defaultPath: String): StorageInterface =
        FileManager(defaultPath)

}