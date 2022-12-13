package com.thoughtworks.ark.webview

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewClientImpl(
    val updateTitle: (String) -> Unit,
    val updateErrorState: (Boolean) -> Unit,
    val shouldOverrideUrl: (String) -> Boolean = { false }
) : WebViewClient() {

    override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
        super.onReceivedError(view, request, error)
        updateErrorState(true)
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val url = request.url.toString()
        return shouldOverrideUrl(url)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        updateErrorState(false)
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        val title = view.title
        title?.let { updateTitle(it) }
    }
}