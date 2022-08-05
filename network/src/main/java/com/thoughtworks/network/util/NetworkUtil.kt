package com.thoughtworks.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.thoughtworks.network.callback.BaseCallModel
import com.thoughtworks.network.callback.RetrofitCallback
import com.thoughtworks.network.entity.RetrofitResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import kotlin.coroutines.resume

suspend fun <T : Any> performRequest(call: Call<BaseCallModel<T>>?) : RetrofitResponse =
    suspendCancellableCoroutine {
        call?.enqueue(
            object : RetrofitCallback<T>() {
                override fun onSuccess(data: T) {
                    it.resume(RetrofitResponse.Success(data))
                }

                override fun onFailed(msg: String) {
                    it.resume(RetrofitResponse.Error(Exception(msg)))
                }
            }
        ) ?: it.resume(RetrofitResponse.Error(Exception("network is not connect")))
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
