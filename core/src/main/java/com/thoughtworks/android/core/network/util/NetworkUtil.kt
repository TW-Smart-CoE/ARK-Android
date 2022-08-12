package com.thoughtworks.android.core.network.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun hasNetworkConnect(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
        return it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    } ?: false
}