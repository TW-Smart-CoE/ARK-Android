package com.thoughtworks.android.core.network.entity

sealed class Result<out T : Any> {
    object Loading : Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data class Success<out T : Any>(val data: T?) : Result<T>()
}