package com.thoughtworks.ark.webview

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewClientImpl(
    val shouldOverrideUrl: (String) -> Boolean = { false }
) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val url = request.url.toString()
        return shouldOverrideUrl(url)
    }

    override fun onPageFinished(view: WebView, url: String) {
        println(url)
        super.onPageFinished(view, url)
    }
}