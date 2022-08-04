package com.thoughtworks.network.callback

import com.thoughtworks.network.entity.RetrofitResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import java.net.ConnectException
import java.net.SocketTimeoutException

abstract class RetrofitCallback<T:Any> : Callback<BaseCallModel<T>>  {
    abstract fun onSuccess(data: T)

    abstract fun onFailed(msg: String)
    override fun onResponse(call: Call<BaseCallModel<T>>, response: Response<BaseCallModel<T>>) {
        when(response.raw().code()) {
            200 -> {
                when(response.body()?.errorCode) {
                    0 -> onSuccess(response.body()!!.data)
                    else -> response.body()?.message?.let { onFailed(it) }
                }
            }
            else -> onFailure(call, RuntimeException("response error"))
        }
    }

    override fun onFailure(call: Call<BaseCallModel<T>>, t: Throwable) {
        val errorMsg = when (t) {
            is SocketTimeoutException -> "sock exception"
            is ConnectException -> "connect exception"
            else -> "other exception"
        }
        onFailed(errorMsg)
    }
}