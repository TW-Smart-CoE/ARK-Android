package com.thoughtworks.ark.core.network.client.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

abstract class BaseRetrofit constructor(private val networkJson: Json) {
    fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit =
        Retrofit.Builder().apply {
            baseUrl(baseUrl)
            addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            client(client)
            initRetrofit(this)
        }.build()

    abstract fun initRetrofit(builder: Retrofit.Builder)
}