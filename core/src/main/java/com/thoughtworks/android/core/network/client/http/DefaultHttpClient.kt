package com.thoughtworks.android.core.network.client.http

import android.content.Context
import com.thoughtworks.android.core.BuildConfig
import com.thoughtworks.android.core.network.interceptor.NetConnectInterceptor
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
        if (BuildConfig.DEBUG) {
            addLoggingInterceptor(builder)
        }
    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder) {
        builder.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).addInterceptor(NetConnectInterceptor(context))
    }

    companion object {
        const val TIME_OUT = 5L
        const val CONNECT_TIME_OUT = 15L
    }
}