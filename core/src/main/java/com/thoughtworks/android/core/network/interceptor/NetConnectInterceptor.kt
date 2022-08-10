package com.thoughtworks.android.core.network.interceptor

import android.content.Context
import com.thoughtworks.android.core.network.entity.NetworkConnectException
import com.thoughtworks.android.core.network.util.hasNetworkConnect
import okhttp3.Interceptor
import okhttp3.Response

class NetConnectInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (hasNetworkConnect(context)) {
            return chain.proceed(builder.build())
        } else {
            throw NetworkConnectException("network is not connect")
        }
    }
}