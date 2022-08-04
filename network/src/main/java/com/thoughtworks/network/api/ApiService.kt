package com.thoughtworks.network.api

import retrofit2.http.GET

interface ApiService {
    @GET("/list/data")
    suspend fun getAllData()
}