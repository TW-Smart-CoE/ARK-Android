package com.thoughtworks.ark.core.network.client.http

import android.content.Context
import com.thoughtworks.ark.core.network.interceptor.NetworkConnectionInterceptor
import com.thoughtworks.ark.core.utils.isDevEnvironment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

open class DefaultHttpClient(private val context: Context) {
    var okHttpClient: OkHttpClient

    init {
        okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            initHttpClient(this)
        }.build()
    }

    private fun initHttpClient(builder: OkHttpClient.Builder) {
        addLoggingInterceptor(builder)
        addInterceptors(builder)
    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder) {
        builder.takeIf { isDevEnvironment() }?.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
    }

    private fun addInterceptors(builder: OkHttpClient.Builder) {
        builder.addInterceptor(NetworkConnectionInterceptor(context))
    }

    companion object {
        const val TIME_OUT = 5L
        const val CONNECT_TIME_OUT = 15L
    }
}