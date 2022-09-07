package com.thoughtworks.ark.core.network.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.thoughtworks.ark.core.network.entity.NetworkReachableException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkReachableInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (isNetworkReachable(context)) {
            return chain.proceed(builder.build())
        } else {
            throw NetworkReachableException()
        }
    }

    private fun isNetworkReachable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            return hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        } ?: false
    }
}