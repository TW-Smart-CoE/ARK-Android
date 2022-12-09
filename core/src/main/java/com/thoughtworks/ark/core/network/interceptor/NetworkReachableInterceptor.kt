package com.thoughtworks.ark.core.network.interceptor

import android.content.Context
import com.thoughtworks.ark.core.network.entity.NetworkReachableException
import com.thoughtworks.ark.core.utils.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

class NetworkReachableInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (NetworkUtil.isNetworkReachable(context)) {
            return chain.proceed(builder.build())
        } else {
            throw NetworkReachableException()
        }
    }
}