package com.thoughtworks.ark.core.network.client.retrofit

import retrofit2.Retrofit

class DefaultRetrofit : BaseRetrofit() {
    override fun initRetrofit(builder: Retrofit.Builder) = Unit
}