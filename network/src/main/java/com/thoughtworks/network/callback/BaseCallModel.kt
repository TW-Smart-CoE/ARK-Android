package com.thoughtworks.network.callback

open class BaseCallModel<T>(
    val errorCode: Int,
    val errorMsg: String,
    val data: T
)