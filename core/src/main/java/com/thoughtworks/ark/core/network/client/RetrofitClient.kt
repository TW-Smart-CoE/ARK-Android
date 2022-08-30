package com.thoughtworks.ark.core.network.client

import android.content.Context
import com.thoughtworks.ark.core.network.client.http.DefaultHttpClient
import com.thoughtworks.ark.core.network.client.retrofit.BaseRetrofit
import com.thoughtworks.ark.core.network.client.retrofit.DefaultRetrofit
import retrofit2.Retrofit

class RetrofitClient(private val context: Context) {
    private lateinit var httpClient: DefaultHttpClient
    private lateinit var baseRetrofit: BaseRetrofit
    private val retrofitMap: HashMap<String, Retrofit> = hashMapOf()

    private fun initRetrofit(
        baseUrl: String,
        customHttpClient: DefaultHttpClient = DefaultHttpClient(context),
        customRetrofit: BaseRetrofit = DefaultRetrofit()
    ) {
        httpClient = customHttpClient
        baseRetrofit = customRetrofit
        val retrofit = baseRetrofit.createRetrofit(baseUrl, httpClient.okHttpClient)
        retrofitMap[baseUrl] = retrofit
    }

    fun <T> createService(clazz: Class<T>, baseUrl: String): T {
        if (retrofitMap[baseUrl] == null) {
            initRetrofit(baseUrl)
        }
        return retrofitMap[baseUrl]!!.create(clazz)
    }

    fun <T : DefaultHttpClient> setupCustomHttpClient(httpClient: T) {
        this.httpClient = httpClient
        retrofitMap.clear()
    }

    fun <T : BaseRetrofit> setupCustomRetrofit(retrofitClient: T) {
        this.baseRetrofit = retrofitClient
        retrofitMap.clear()
    }
}