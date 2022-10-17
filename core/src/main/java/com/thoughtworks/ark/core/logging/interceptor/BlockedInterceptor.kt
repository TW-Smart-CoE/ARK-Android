package com.thoughtworks.ark.core.logging.interceptor

class BlockedInterceptor(private val tags: List<String>): Interceptor {

    override fun intercept(tag: String?, priority: Int): Boolean {
        return tags.contains(tag)
    }
}
