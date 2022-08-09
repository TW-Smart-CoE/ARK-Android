package com.thoughtworks.android.core.network.entity

sealed class RetrofitResponse<T> {
    class Loading<T> : RetrofitResponse<T>()
    data class Error<T>(val errorMsg: String): RetrofitResponse<T>()
    data class Success<T>(val data: T): RetrofitResponse<T>()
}