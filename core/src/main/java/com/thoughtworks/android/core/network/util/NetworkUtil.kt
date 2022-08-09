package com.thoughtworks.android.core.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.thoughtworks.android.core.network.entity.RetrofitResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

suspend fun <T : Any> performFlowRequest(apiCall: suspend () -> Response<T>) : Flow<RetrofitResponse<T>> {
    return flow {
        emit(RetrofitResponse.Loading())
        emit(getResponse(apiCall))
    }
}

private suspend fun <T> getResponse(apiCall: suspend () -> Response<T>) : RetrofitResponse<T> {
    return try {
        val response = apiCall()
        if(response.isSuccessful) {
            RetrofitResponse.Success(response.body()!!)
        } else {
            RetrofitResponse.Error("result is error.")
        }
    } catch (e:SocketTimeoutException) {
        RetrofitResponse.Error("socket time out exception")
    } catch (e:HttpException) {
        RetrofitResponse.Error("http connect exception")
    } catch (e: IOException) {
        RetrofitResponse.Error(e.message?:"io exception")
    } catch (e: Exception) {
        RetrofitResponse.Error("other exception")
    }
}

fun hasNetworkConnect(context: Context): Boolean {
    var isConnect = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    connectivityManager.run {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getNetworkCapabilities(activeNetwork)?.run {
                isConnect = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)    -> true
                    else -> false
                }
            }
        } else {
            activeNetworkInfo?.run {
                isConnect = when(type) {
                    ConnectivityManager.TYPE_WIFI  -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    else -> false
                }
            }
        }
    }
    return isConnect
}
