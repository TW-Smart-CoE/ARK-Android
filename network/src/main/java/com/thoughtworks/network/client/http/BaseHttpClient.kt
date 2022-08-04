package com.thoughtworks.network.client.http

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

abstract class BaseHttpClient {
    var okHttpClient:OkHttpClient
    init {
        okHttpClient = OkHttpClient.Builder().apply {
            callTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            connectTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            readTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            writeTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            initHttpClient(this)
        }.build()
    }

    abstract fun initHttpClient(builder: OkHttpClient.Builder)

    companion object {
        const val TIME_OUT = 5000L
    }
}