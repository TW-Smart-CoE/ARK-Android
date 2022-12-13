package com.thoughtworks.ark.webview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class WebViewItem private constructor(
    val url: String,
    val title: String = "",
    val enableTitleBar: Boolean = true,
    val enableProgressBar: Boolean = true
) : Parcelable {
    companion object {
        fun fromUrl(
            url: String,
            title: String = "",
            enableTitleBar: Boolean = true,
            enableProgressBar: Boolean = true
        ): WebViewItem {
            return WebViewItem(url, title, enableTitleBar, enableProgressBar)
        }

        fun fromAsset(
            assetPath: String,
            title: String = "",
            enableTitleBar: Boolean = true,
            enableProgressBar: Boolean = true
        ): WebViewItem {
            return WebViewItem("file:///android_asset/$assetPath", title, enableTitleBar, enableProgressBar)
        }
    }
}