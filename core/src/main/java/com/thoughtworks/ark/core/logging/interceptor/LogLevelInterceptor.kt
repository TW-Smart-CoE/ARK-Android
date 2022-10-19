package com.thoughtworks.ark.core.logging.interceptor

import android.util.Log

class LogLevelInterceptor(private val logLevel: Int = Log.VERBOSE) : Interceptor {

    override fun intercept(tag: String?, priority: Int): Boolean {
        return priority < logLevel
    }
}
