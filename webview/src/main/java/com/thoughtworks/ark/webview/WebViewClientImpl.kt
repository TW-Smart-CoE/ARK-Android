package com.thoughtworks.ark.webview

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewClientImpl(
    val updateTitle: (String) -> Unit,
    val updateErrorState: (Boolean) -> Unit,
    val shouldOverrideUrl: (String) -> Boolean = { true }
) : WebViewClient() {

    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
        super.onReceivedError(view, request, error)
        if (request.isForMainFrame && isCommonError(error.errorCode)) {
            view.loadUrl(BLANK_PAGE)
            updateErrorState(true)
        }
    }

    private fun isCommonError(errorCode: Int): Boolean {
        return errorCode == ERROR_CONNECT || errorCode == ERROR_HOST_LOOKUP || errorCode == ERROR_TIMEOUT
    }

    override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
        val url = request.url.toString()
        if (url.isValidUrl()) return false

        return shouldOverrideUrl(url)
    }

    override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        if (url != BLANK_PAGE) {
            updateErrorState(false)
        }
    }

    override fun onPageFinished(view: WebView, url: String) {
        super.onPageFinished(view, url)
        val title = view.title
        title?.let {
            if (it != BLANK_PAGE) {
                updateTitle(it)
            } else {
                updateTitle("")
            }
        }
    }

    private fun String.isValidUrl(): Boolean {
        return startsWith("http://") || startsWith("https://")
    }

    companion object {
        const val BLANK_PAGE = "about:blank"
    }
}