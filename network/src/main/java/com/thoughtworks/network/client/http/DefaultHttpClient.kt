package com.thoughtworks.network.client.http

import android.content.Context
import android.util.Log
import com.thoughtworks.network.BuildConfig
import com.thoughtworks.network.entity.NetworkNotConnectException
import com.thoughtworks.network.util.hasNetworkConnect
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class DefaultHttpClient(val context: Context) : BaseHttpClient() {
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
        ).addInterceptor{ chain ->
            if(hasNetworkConnect(context)) {
                return@addInterceptor chain.proceed(chain.request())
            } else {
                Log.e("tag","network is not connect")
                throw NetworkNotConnectException();
            }
        }
    }
}