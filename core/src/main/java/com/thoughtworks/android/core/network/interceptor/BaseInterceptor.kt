package com.thoughtworks.android.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

open class BaseInterceptor(private val headers: Map<String, String>?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        headers?.run {
            forEach {
                builder.addHeader(it.key, it.value).build()
            }
        }
        return chain.proceed(builder.build())
    }
}