package com.thoughtworks.ark.core.network.client.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRetrofit {
    fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(baseUrl)
            addConverterFactory(GsonConverterFactory.create())
            client(client)
            initRetrofit(this)
        }.build()

    abstract fun initRetrofit(builder: Retrofit.Builder)
}