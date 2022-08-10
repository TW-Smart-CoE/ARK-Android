package com.thoughtworks.android.core.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.net.ParseException
import com.google.gson.JsonParseException
import com.thoughtworks.android.core.network.entity.NetworkConnectException
import com.thoughtworks.android.core.network.entity.RetrofitResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.json.JSONException
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

suspend fun <T : Any> performFlowRequest(apiCall: suspend () -> Response<T>): Flow<RetrofitResponse<T>> {
    return flow {
        emit(RetrofitResponse.Loading())
        emit(getResponse(apiCall))
    }
}

private suspend fun <T> getResponse(apiCall: suspend () -> Response<T>): RetrofitResponse<T> {
    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            RetrofitResponse.Success(response.body()!!)
        } else {
            RetrofitResponse.Error(0, "result is error.")
        }
    } catch (throwable: Throwable) {
        RetrofitResponse.Error(
            when (throwable) {
                is HttpException -> STATUS_EXCEPTION
                is SocketTimeoutException -> CONNECTION_TIMEOUT
                is SSLHandshakeException -> HTTPS_HANDSHAKE_FAILED
                is JSONException, is JsonParseException, is ParseException -> JSON_EXCEPTION
                is UnknownHostException, is ConnectException, is SocketException, is NetworkConnectException -> NO_CONNECTION
                is IOException -> IO_EXCEPTION
                else -> UNKNOWN
            }
        )
    }
}

fun hasNetworkConnect(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
        return it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
    return false
}

const val STATUS_EXCEPTION = 1
const val CONNECTION_TIMEOUT = 2
const val HTTPS_HANDSHAKE_FAILED = 3
const val JSON_EXCEPTION = 4
const val NO_CONNECTION = 5
const val UNKNOWN = 6
const val IO_EXCEPTION = 7