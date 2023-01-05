package com.thoughtworks.ark.deeplink

import androidx.core.net.toUri

object DeeplinkManager {
    private const val KEY_DEST = "dest"

    fun parseDestScheme(originScheme: String): String {
        if (originScheme.isEmpty()) return ""
        val uri = originScheme.toUri()
        return uri.getQueryParameter(KEY_DEST) ?: ""
    }
}