package com.thoughtworks.network.client.http

import com.thoughtworks.network.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class DefaultHttpClient : BaseHttpClient() {
    override fun initHttpClient(builder: OkHttpClient.Builder) {
        if (BuildConfig.DEBUG) {
            addLoggingInterceptor(builder)
        }
    }

    private fun addLoggingInterceptor(builder:OkHttpClient.Builder) {
        builder.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }
}