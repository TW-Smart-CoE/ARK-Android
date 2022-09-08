package com.thoughtworks.ark.core.network.client

import android.content.Context
import com.thoughtworks.ark.core.network.client.http.HttpClient
import com.thoughtworks.ark.core.network.client.retrofit.BaseRetrofit
import com.thoughtworks.ark.core.network.client.retrofit.DefaultRetrofit
import kotlinx.serialization.json.Json
import retrofit2.Retrofit

class ApiEndPoints(private val context: Context, private val networkJson: Json) {
    private val retrofitMap: HashMap<String, Retrofit> = hashMapOf()

    fun <S> createService(clazz: Class<S>, baseUrl: String): S =
        createService(clazz, baseUrl, HttpClient(context), DefaultRetrofit(networkJson))

    fun <T : HttpClient, R : BaseRetrofit, S> createService(
        clazz: Class<S>,
        baseUrl: String,
        httpClient: T,
        baseRetrofit: R,
    ): S {
        val retrofit = baseRetrofit.createRetrofit(baseUrl, httpClient.okHttpClient)
        retrofitMap[baseUrl] = retrofit
        return retrofit.create(clazz)
    }
}