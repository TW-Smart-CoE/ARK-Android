package com.thoughtworks.network.client

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

abstract class BaseHttpClient {
    val client: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .callTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .readTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
        setupLoggingInterceptor()?.let {
            builder.addInterceptor(it)
        }
        handleHttpClient(builder)
        builder.build()
    }

    abstract fun handleHttpClient(builder: OkHttpClient.Builder)

    abstract fun setupLoggingInterceptor(): Interceptor?

    companion object {
        const val TIME_OUT = 5000L
    }
}