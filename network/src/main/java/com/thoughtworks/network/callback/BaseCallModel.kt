package com.thoughtworks.network.callback

open class BaseCallModel<T>(
    val errorCode: Int,
    val message: String,
    val data: T
)