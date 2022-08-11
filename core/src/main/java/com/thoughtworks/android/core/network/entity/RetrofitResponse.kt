package com.thoughtworks.android.core.network.entity

sealed class RetrofitResponse<T> {
    class Loading<T> : RetrofitResponse<T>()
    data class Error<T>(val code: Int = ERROR_CODE_DEFAULT) :
        RetrofitResponse<T>()

    data class Success<T>(val data: T) : RetrofitResponse<T>()

    companion object {
        const val ERROR_CODE_DEFAULT = 0
        const val ERROR_CODE_STATUS_EXCEPTION = 1
        const val ERROR_CODE_CONNECTION_TIMEOUT = 2
        const val ERROR_CODE_HTTPS_HANDSHAKE_FAILED = 3
        const val ERROR_CODE_JSON_EXCEPTION = 4
        const val ERROR_CODE_IO_EXCEPTION = 5
        const val ERROR_CODE_NETWORK_CONNECTION = 6
        const val ERROR_CODE_UNKNOWN = 7
    }
}