package com.thoughtworks.ark.core.network.client.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRetrofit {

    fun createRetrofit(baseUrl: String, httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(baseUrl)
            addConverterFactory(GsonConverterFactory.create())
            client(httpClient)
            initRetrofit(this)
        }.build()

    abstract fun initRetrofit(builder: Retrofit.Builder)
}