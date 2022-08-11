package com.thoughtworks.android.core.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.net.ParseException
import com.google.gson.JsonParseException
import com.thoughtworks.android.core.network.entity.NetworkConnectionException
import com.thoughtworks.android.core.network.entity.RetrofitResponse
import com.thoughtworks.android.core.network.entity.RetrofitResponse.Companion.ERROR_CODE_CONNECTION_TIMEOUT
import com.thoughtworks.android.core.network.entity.RetrofitResponse.Companion.ERROR_CODE_HTTPS_HANDSHAKE_FAILED
import com.thoughtworks.android.core.network.entity.RetrofitResponse.Companion.ERROR_CODE_IO_EXCEPTION
import com.thoughtworks.android.core.network.entity.RetrofitResponse.Companion.ERROR_CODE_JSON_EXCEPTION
import com.thoughtworks.android.core.network.entity.RetrofitResponse.Companion.ERROR_CODE_NETWORK_CONNECTION
import com.thoughtworks.android.core.network.entity.RetrofitResponse.Companion.ERROR_CODE_STATUS_EXCEPTION
import com.thoughtworks.android.core.network.entity.RetrofitResponse.Companion.ERROR_CODE_UNKNOWN
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
            RetrofitResponse.Error()
        }
    } catch (throwable: Throwable) {
        parseError(throwable)
    }
}

private fun <T> parseError(throwable: Throwable): RetrofitResponse.Error<T> {
    return RetrofitResponse.Error(
        when (throwable) {
            is HttpException -> ERROR_CODE_STATUS_EXCEPTION
            is SocketTimeoutException -> ERROR_CODE_CONNECTION_TIMEOUT
            is SSLHandshakeException -> ERROR_CODE_HTTPS_HANDSHAKE_FAILED
            is JSONException,
            is JsonParseException,
            is ParseException -> ERROR_CODE_JSON_EXCEPTION
            is UnknownHostException,
            is ConnectException,
            is SocketException,
            is NetworkConnectionException -> ERROR_CODE_NETWORK_CONNECTION
            is IOException -> ERROR_CODE_IO_EXCEPTION
            else -> ERROR_CODE_UNKNOWN
        }
    )
}

fun hasNetworkConnect(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
        return it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    } ?: false
}