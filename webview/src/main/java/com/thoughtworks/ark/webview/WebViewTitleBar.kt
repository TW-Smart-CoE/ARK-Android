package com.thoughtworks.ark.webview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class WebViewTitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private var onBackClick: () -> Unit = {}
    private var onCloseClick: () -> Unit = {}

    private val back: ImageView
    private val close: ImageView
    private val title: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.web_view_title_bar, this)

        back = findViewById(R.id.back)
        close = findViewById(R.id.close)
        title = findViewById(R.id.title)

        back.setOnClickListener { onBackClick() }
        close.setOnClickListener { onCloseClick() }
    }

    fun setOnBackClickListener(clickListener: () -> Unit) {
        onBackClick = clickListener
    }

    fun setOnCloseClickListener(closeListener: () -> Unit) {
        onCloseClick = closeListener
    }

    fun setTittle(title: String?) {
        this.title.text = title
    }
}