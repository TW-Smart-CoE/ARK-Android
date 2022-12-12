package com.thoughtworks.ark.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var title: View

    private val webViewItem by lazy { intent.getParcelableExtra(KEY_WEB_DATA) ?: WebViewItem.fromUrl("") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.web_view)
        progressBar = findViewById(R.id.progress_bar)
        title = findViewById(R.id.title)

        setupWebView()
        setupUI()
        loadUrl()

        handleBackPressed()
    }

    private fun handleBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!goBack()) {
                    close()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun setupWebView() {
        webView.settings.apply {
            setSupportZoom(false)
            setGeolocationEnabled(true)

            @SuppressLint("SetJavaScriptEnabled")
            javaScriptEnabled = true
            domStorageEnabled = true
            databaseEnabled = true
            allowFileAccess = true

            cacheMode = WebSettings.LOAD_DEFAULT
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        webView.isVerticalScrollBarEnabled = false
        webView.webViewClient = WebViewClientImpl {
            handleOverrideUrl(it)
        }
        webView.webChromeClient = WebChromeClientImpl(
            updateTitle = {
                setTitle(it)
            },
            updateProgress = { progress, completed ->
                if (webViewItem.enableProgressBar) {
                    if (completed) {
                        progressBar.visibility = GONE
                    } else {
                        progressBar.visibility = VISIBLE
                    }
                }
                progressBar.progress = progress
            }
        )
    }

    private fun setupUI() {
        title.visibility = if (webViewItem.enableHeader) VISIBLE else GONE
        progressBar.visibility = if (webViewItem.enableProgressBar) VISIBLE else GONE

        setTitle(webViewItem.title)
    }

    private fun loadUrl() {
        if (webViewItem.url.isNotEmpty()) {
            webView.loadUrl(webViewItem.url)
        }
    }

    private fun setTitle(title: String) {
        // todo
    }

    private fun goBack(): Boolean {
        return if (webView.canGoBack()) {
            webView.goBack()
            true
        } else {
            false
        }
    }

    private fun handleOverrideUrl(url: String): Boolean {
        // todo
        return false
    }

    private fun close() {
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        (webView.parent as ViewGroup).removeView(webView)
        webView.destroy()
    }

    companion object {
        const val KEY_WEB_DATA = "key_web_data"

        fun Context.openWebViewFromUrl(
            url: String,
            title: String = "",
            enableHeader: Boolean = true,
            enableProgressBar: Boolean = true
        ) {
            val webViewItem = WebViewItem.fromUrl(url, title, enableHeader, enableProgressBar)
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(KEY_WEB_DATA, webViewItem)
            startActivity(intent)
        }

        fun Context.openWebViewFromAsset(
            assetPath: String,
            title: String = "",
            enableHeader: Boolean = true,
            enableProgressBar: Boolean = true
        ) {
            val webViewItem = WebViewItem.fromAsset(assetPath, title, enableHeader, enableProgressBar)
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(KEY_WEB_DATA, webViewItem)
            startActivity(intent)
        }
    }
}