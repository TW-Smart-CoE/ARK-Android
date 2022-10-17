package com.thoughtworks.ark.core.logging.interceptor

interface Interceptor {

    /**
     * Decide whether to disable this log according to [priority] and [tag]
     *
     * @return true to disable this log
     */
    fun intercept(tag: String?, priority: Int): Boolean = false
}
