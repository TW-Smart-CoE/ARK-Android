package com.thoughtworks.network.manager

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitManager() {
    private val httpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()
            .callTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .readTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
        handleHttpClient(builder)
        builder.build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(HOST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    fun <T> createService(clazz:Class<T>):T = retrofit.create(clazz)

    fun handleHttpClient(builder: OkHttpClient.Builder) {
    }

    companion object {
        const val TIME_OUT = 5000L
        const val HOST_URL = "www.example.com"
    }
}