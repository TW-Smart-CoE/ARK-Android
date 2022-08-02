package com.thoughtworks.network.entity

sealed class RetrofitResponse<T:Any> {
    object Loading : RetrofitResponse<Nothing>()
    data class Error(val exception: Exception): RetrofitResponse<Nothing>()
    data class Success<T: Any>(val data: T): RetrofitResponse<T>()
    data class Incomplete<T: Any>(val data: T,val error: Exception): RetrofitResponse<T>()
}