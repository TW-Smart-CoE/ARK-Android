package com.thoughtworks.network.client

import com.thoughtworks.network.api.ApiService
import com.thoughtworks.network.client.http.BaseHttpClient
import com.thoughtworks.network.client.http.DefaultHttpClient
import com.thoughtworks.network.client.retrofit.BaseRetrofit
import com.thoughtworks.network.client.retrofit.DefaultRetrofit
import retrofit2.Retrofit

class RetrofitClient {
    private lateinit var httpClient: BaseHttpClient
    private lateinit var retrofitClient: BaseRetrofit
    private val retrofitMap:MutableMap<String,Retrofit> = hashMapOf()
    init {
        initRetrofit()
    }

    private fun initRetrofit(baseUrl:String = HOST_URL) {
        httpClient = DefaultHttpClient()
        retrofitClient = DefaultRetrofit()
        val retrofit = retrofitClient.createRetrofit(baseUrl, httpClient.okHttpClient)
        retrofitMap[baseUrl] = retrofit;
    }

    fun <T> createService(clazz:Class<T>, baseUrl:String):T {
        if(retrofitMap[baseUrl] == null) {
            initRetrofit(baseUrl)
        }
        return retrofitMap[baseUrl]!!.create(clazz)
    }

    fun <T:BaseHttpClient> setupCustomHttpClient(httpClient: T) {
        this.httpClient = httpClient
        retrofitMap.clear()
    }

    fun <T:BaseRetrofit> setupCustomRetrofit(retrofitClient: T) {
        this.retrofitClient = retrofitClient
        retrofitMap.clear()
    }

    companion object {
        const val HOST_URL = "http://www.baidu.com"
    }
}