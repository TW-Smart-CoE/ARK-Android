package com.thoughtworks.ark.webview

import android.app.AlertDialog
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.text.HtmlCompat

class WebChromeClientImpl(
    val updateTitle: (String) -> Unit,
    val updateProgress: (Int, Boolean) -> Unit
) : WebChromeClient() {

    override fun onReceivedTitle(view: WebView, title: String) {
        super.onReceivedTitle(view, title)
        updateTitle(title)
    }

    override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult?): Boolean {
        AlertDialog.Builder(view.context)
            .setMessage(HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_LEGACY))
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.dismiss()
                result?.confirm()
            }
            .setNegativeButton(android.R.string.cancel) { dialog, _ ->
                dialog.dismiss()
                result?.cancel()
            }
            .setCancelable(false)
            .show()
        return true
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        updateProgress(newProgress, newProgress == MAX_PROGRESS)
    }

    companion object {
        const val MAX_PROGRESS = 100
    }
}