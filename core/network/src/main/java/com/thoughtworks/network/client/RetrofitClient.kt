package com.thoughtworks.network.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    private lateinit var retrofit:Retrofit
    private lateinit var okHttpClient: OkHttpClient
    init {
        initRetrofit(
            initOkHttpClient = {
                addLoggingInterceptor(it,true)
            }
        )
    }

    private fun initRetrofit(
        initOkHttpClient: (OkHttpClient.Builder)-> Unit = {},
        initRetrofit: (Retrofit.Builder) -> Unit = {}
    ) {
        okHttpClient = OkHttpClient.Builder().apply {
            callTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            connectTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            readTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            writeTimeout(TIME_OUT, TimeUnit.MICROSECONDS)
            initOkHttpClient(this)
        }.build()

        retrofit = Retrofit.Builder().apply {
            baseUrl(HOST_URL)
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
            initRetrofit(this)
        }.build()
    }

    private fun addLoggingInterceptor(builder:OkHttpClient.Builder, isDebug:Boolean = true) {
        if(isDebug) {
            builder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }
    }

    fun <T> createService(clazz:Class<T>):T = retrofit.create(clazz)

    companion object {
        const val HOST_URL = "www.example.com"
        const val TIME_OUT = 5000L
    }
}