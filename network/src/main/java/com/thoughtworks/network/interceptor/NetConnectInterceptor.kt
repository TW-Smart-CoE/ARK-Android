package com.thoughtworks.network.interceptor

import android.content.Context
import android.util.Log
import com.thoughtworks.network.entity.NetworkNotConnectException
import com.thoughtworks.network.util.hasNetworkConnect
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetConnectInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if(hasNetworkConnect(context)) {
            return chain.proceed(builder.build())
        } else {
            Log.e("tag","network is not connect")
            throw IOException()
        }
    }
}