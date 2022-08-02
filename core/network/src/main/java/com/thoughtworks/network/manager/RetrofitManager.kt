package com.thoughtworks.network.manager

import android.util.Log
import com.thoughtworks.network.client.BaseHttpClient
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitManager {
    private var httpClient: BaseHttpClient = object : BaseHttpClient() {
        override fun handleHttpClient(builder: OkHttpClient.Builder) {
            Log.d("TAG","custom handle http client")
        }

        override fun setupLoggingInterceptor(): Interceptor? = HttpLoggingInterceptor()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(HOST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.client)
        .build()

    fun <T> createService(clazz:Class<T>):T = retrofit.create(clazz)

    fun <T : BaseHttpClient> setHttpClient(client:T) {
        httpClient = client
    }

    companion object {
        const val HOST_URL = "www.example.com"
    }
}