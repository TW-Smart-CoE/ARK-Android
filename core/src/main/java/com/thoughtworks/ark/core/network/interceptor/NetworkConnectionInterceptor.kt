package com.thoughtworks.ark.core.network.interceptor

import android.content.Context
import com.thoughtworks.ark.core.network.entity.NetworkConnectionException
import com.thoughtworks.ark.core.network.util.hasNetworkConnect
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (hasNetworkConnect(context)) {
            return chain.proceed(builder.build())
        } else {
            throw NetworkConnectionException()
        }
    }
}