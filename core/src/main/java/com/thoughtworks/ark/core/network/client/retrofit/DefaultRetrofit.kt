package com.thoughtworks.ark.core.network.client.retrofit

import kotlinx.serialization.json.Json
import retrofit2.Retrofit

class DefaultRetrofit(networkJson: Json) : BaseRetrofit(networkJson) {
    override fun initRetrofit(builder: Retrofit.Builder) = Unit
}