package com.thoughtworks.ark.webview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView

class WebViewTitleBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private var onBackClick: () -> Unit = {}
    private var onCloseClick: () -> Unit = {}

    private val btnBack: Button
    private val btnClose: Button
    private val tvTitle: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.web_view_title_bar, this)

        btnBack = findViewById(R.id.btn_back)
        btnClose = findViewById(R.id.btn_close)
        tvTitle = findViewById(R.id.title)

        btnBack.setOnClickListener { onBackClick() }
        btnClose.setOnClickListener { onCloseClick() }
    }

    fun setOnBackClickListener(clickListener: () -> Unit) {
        onBackClick = clickListener
    }

    fun setOnCloseClickListener(closeListener: () -> Unit) {
        onCloseClick = closeListener
    }

    fun setTittle(title: String?) {
        tvTitle.text = title
    }
}