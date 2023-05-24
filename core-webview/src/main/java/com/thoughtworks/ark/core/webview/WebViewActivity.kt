package com.thoughtworks.ark.core.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.ark.core.utils.DeviceUtils

class WebViewActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var titleBar: WebViewTitleBar
    private lateinit var errorView: View

    @Suppress("DEPRECATION")
    private val webViewItem by lazy {
        if (DeviceUtils.isMeetAPILevel(Build.VERSION_CODES.TIRAMISU)) {
            intent.getParcelableExtra(KEY_WEB_DATA, WebViewItem::class.java) ?: WebViewItem.fromUrl(
                ""
            )
        } else {
            intent.getParcelableExtra(KEY_WEB_DATA) ?: WebViewItem.fromUrl("")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (webViewItem.url.isEmpty()) {
            finish()
            return
        }

        setupUI()
        setupWebView()

        loadUrl()

        handleBackPressed()
    }

    private fun setupUI() {
        setContentView(R.layout.activity_web_view)
        webView = findViewById(R.id.web_view)
        titleBar = findViewById(R.id.title_bar)
        progressBar = findViewById(R.id.progress_bar)
        errorView = findViewById(R.id.error_view)

        titleBar.visibility = if (webViewItem.enableTitleBar) View.VISIBLE else View.GONE
        progressBar.visibility = if (webViewItem.enableProgressBar) View.VISIBLE else View.GONE

        errorView.setOnClickListener {
            backOrFinish()
        }

        titleBar.setOnBackClickListener {
            backOrFinish()
        }
        titleBar.setOnCloseClickListener { finish() }
        titleBar.setTittle(webViewItem.title)
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
        webView.webViewClient = WebViewClientImpl(
            updateTitle = {
                setTitle(it)
            },
            updateErrorState = {
                errorView.visibility = if (it) View.VISIBLE else View.GONE
            }
        )
        webView.webChromeClient = WebChromeClientImpl(
            updateTitle = {
                setTitle(it)
            },
            updateProgress = { progress, completed ->
                if (webViewItem.enableProgressBar) {
                    if (completed) {
                        progressBar.visibility = View.INVISIBLE
                    } else {
                        progressBar.visibility = View.VISIBLE
                    }
                }
                progressBar.progress = progress
            }
        )
    }

    private fun handleBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backOrFinish()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun backOrFinish() {
        if (!goBack()) {
            finish()
        }
    }

    private fun loadUrl() {
        if (webViewItem.url.isNotEmpty()) {
            webView.loadUrl(webViewItem.url)
        }
    }

    private fun setTitle(title: String) {
        if (webViewItem.title.isEmpty()) {
            titleBar.setTittle(title)
        }
    }

    private fun goBack(): Boolean {
        return if (webView.canGoBack()) {
            webView.goBack()
            true
        } else {
            false
        }
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
            enableTitleBar: Boolean = true,
            enableProgressBar: Boolean = true
        ) {
            val webViewItem = WebViewItem.fromUrl(url, title, enableTitleBar, enableProgressBar)
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(KEY_WEB_DATA, webViewItem)
            startActivity(intent)
        }

        fun Context.openWebViewFromAsset(
            assetPath: String,
            title: String = "",
            enableTitleBar: Boolean = true,
            enableProgressBar: Boolean = true
        ) {
            val webViewItem =
                WebViewItem.fromAsset(assetPath, title, enableTitleBar, enableProgressBar)
            val intent = Intent(this, WebViewActivity::class.java)
            intent.putExtra(KEY_WEB_DATA, webViewItem)
            startActivity(intent)
        }
    }
}