package com.thoughtworks.ark.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

open class HeaderInterceptor(private val headers: Map<String, String>?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        headers?.forEach {
            builder.addHeader(it.key, it.value)
        }
        return chain.proceed(builder.build())
    }
}