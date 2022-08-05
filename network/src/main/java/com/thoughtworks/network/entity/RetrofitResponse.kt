package com.thoughtworks.network.entity

sealed class RetrofitResponse {
    object Loading : RetrofitResponse()
    data class Error(val exception: Exception): RetrofitResponse()
    data class Success<T>(val data: T): RetrofitResponse()
}